package com.ty.auth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ty.auth.util.RedisManager;
import com.ty.core.dao.UserMapper;
import com.ty.core.vo.Res;
import com.ty.core.vo.Role;
import com.ty.core.vo.User;
import com.ty.util.CommUtil;
import com.ty.util.codec.Base64;
import com.ty.util.codec.HMACSHA256;
import io.micrometer.core.instrument.util.StringUtils;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisManager redisManager;

    public static final String key = "secretkey";

    public String userVerify(String userName, String passWord, String requestHeader) {
        User user = userMapper.login(userName, passWord);
        if (user != null) {
            String cacheId = "";
            String leftCache;
            long tkTime = System.currentTimeMillis();
            //区分移动端pc端请求
            if (CommUtil.isMobileDevice(requestHeader)){
                cacheId =   "M" + "_" + String.valueOf(user.getId()) + "_" + String.valueOf(tkTime);
                leftCache = "M" + "_" + String.valueOf(user.getId()) + "_";
            }else {
                cacheId =   "P" + "_" + String.valueOf(user.getId()) + "_" + String.valueOf(tkTime);
                leftCache = "P" + "_" + String.valueOf(user.getId()) + "_";
            }
            Set<String> keySet = redisManager.KEYS.keys(leftCache+"*");
            if (keySet.size()>0){
                //如果存在key，就是重复登录，删除以前的缓存记录，之前登录失效，设置新的登录缓存
                String oldKey = keySet.iterator().next();
                redisManager.KEYS.del(oldKey);
            }

            List<String> roles = new ArrayList<>();
            List<String> ress = new ArrayList<>();
            Map<String,Object> roleResMap = new HashMap();
            for (Role role : user.getRoleSet()) {
                roles.add(role.getRoleName());
                for (Res res : role.getResSet()) {
                    ress.add(res.getResName());
                }
            }
            roleResMap.put("roles", roles);
            roleResMap.put("ress", ress);

            String token = getToken(user,tkTime);

            Map<String,Object> sourceMap = new HashMap();
            sourceMap.put("token", token);
            sourceMap.put("source", roleResMap);

            String sourceStr = JSON.toJSONString(sourceMap);

            redisManager.STRINGS.set(cacheId,sourceStr);
            long i = redisManager.KEYS.expired(cacheId,60*60*24*30);

            return sourceStr;
        } else {
            return null;
        }
    }

    public String tokenVerify(HttpServletRequest request){
        String requestHeader = request.getHeader("user-agent");
        String token = request.getHeader("Authorization");
        String[] ts = token.split(",");
        String signature = new String(Base64.decodeBase64(ts[2].getBytes()));

        //重新获取签名
        JSONObject headerJsonObject = new JSONObject();
        headerJsonObject.put("alg", "HS256");
        headerJsonObject.put("typ", "JWT");
        String header = headerJsonObject.toString();
        String headerBase64 = new String(Base64.encodeBase64(header.getBytes()));
        String payloadBase64 = token.split(",")[1];
        String unsignedToken = headerBase64 + "," + payloadBase64;
        String newsignature = HMACSHA256.sha256_HMAC(key, unsignedToken);

        //签名对比
        if (!signature.equals(newsignature)){
            return null;
        }else {
            String payload = new String(Base64.decodeBase64(ts[1].getBytes()));
            JSONObject payloadJsonObject = JSON.parseObject(payload);
            String uid = payloadJsonObject.get("uid").toString();
            String iat = payloadJsonObject.get("iat").toString();
            String cacheId = "";
            if (CommUtil.isMobileDevice(requestHeader)){
                cacheId =   "M" + "_" + uid + "_" + iat;
            }else {
                cacheId =   "P" + "_" + uid + "_" + iat;
            }
            String sourceStr = redisManager.STRINGS.get(cacheId);
            if (StringUtils.isBlank(signature)){
                return null;
            }else {
                return sourceStr;
            }
        }
    }

    public String getToken(User user, long tkTime) {

        JSONObject headerJsonObject = new JSONObject();
        headerJsonObject.put("alg", "HS256");
        headerJsonObject.put("typ", "JWT");
        String header = headerJsonObject.toString();
        String headerBase64 = new String(Base64.encodeBase64(header.getBytes()));

        JSONObject payloadJsonObject = new JSONObject();
        payloadJsonObject.put("uid", user.getId());
        payloadJsonObject.put("iat", tkTime);
        String payload = payloadJsonObject.toString();
        String payloadBase64 = new String(Base64.encodeBase64(payload.getBytes()));

        String unsignedToken = headerBase64 + "," + payloadBase64;
        String signature = HMACSHA256.sha256_HMAC(key, unsignedToken);
        String signatureBase64 = new String(Base64.encodeBase64(signature.getBytes()));

        String token = headerBase64 + "," + payloadBase64 + "," + signatureBase64;

        return token;
    }
}
