package com.ty.auth.controller;

import com.alibaba.fastjson.JSON;
import com.ty.auth.service.AuthService;
import com.ty.core.service.IUserService;
import com.ty.util.PageBean;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserContoller {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthService authService;

    @RequestMapping("/auth/json/findUser.htm")
    public String findUser(HttpServletRequest request, HttpServletResponse response, String currentPage){
        log.info("/auth/json/findUser.htm 用户分页查询。");
        int page = 1;
        if (StringUtils.isNotBlank(currentPage)){
            page = Integer.valueOf(currentPage);
        }
        PageBean pageBean = userService.findUserPage(15,page);

        List list = new ArrayList();
        Map map = new LinkedHashMap();
        map.put("currentPage", pageBean.getCurrentPage());
        map.put("totalPage", pageBean.getTotalPage());
        map.put("pageSize", pageBean.getPageSize());
        map.put("allRow", pageBean.getAllRow());
        map.put("data", JSONArray.fromObject(pageBean.getList()));
        list.add(map);
        return JSON.toJSONString(list);
    }


    @RequestMapping("/auth/json/loginVerify.htm")
    public String loginVerify(HttpServletRequest request, HttpServletResponse response, String userName, String passWord){
        log.info("/auth/json/loginVerify.htm 用户登录信息验证111。");
        String requestHeader = request.getHeader("user-agent");
        String resorceStr = authService.userVerify(userName, passWord,requestHeader);
        response.setHeader("Authorization",resorceStr);
        return resorceStr;
    }

    @RequestMapping("/auth/json/visitVerify.htm")
    public String visitVerify(HttpServletRequest request, HttpServletResponse response){
        log.info("/auth/json/visitVerify.htm 用户访问验证。");
        String resorceStr = authService.tokenVerify(request);
        response.setHeader("Authorization",resorceStr);
        return resorceStr;
    }
}
