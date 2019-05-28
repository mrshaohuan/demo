package com.ty.web.shiro;

import com.ty.core.service.IUserService;
import com.ty.core.vo.Res;
import com.ty.core.vo.Role;
import com.ty.core.vo.User;
import com.ty.util.Md5Encrypt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

//实现AuthorizingRealm接口用户用户认证
public class MyShiroRealm extends AuthorizingRealm {

    //用于用户查询
    @Autowired
    private IUserService userService;


    //角色权限和对应权限添加    当页面触发权限标签时 触发
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //获取用户角色
        Set<Role> roles = user.getRoleSet();
        Iterator<Role> iterator = roles.iterator();
        Set<String> roleSet = new HashSet<String>();

        Set<String> resSet = new HashSet<String>();


        while (iterator.hasNext()){
            Role role = iterator.next();
            roleSet.add(role.getRoleCode());
            Set<Res> roleResSet = role.getResSet();
            Iterator<Res> it = roleResSet.iterator();
            while (it.hasNext()) {
                Res res = it.next();
                resSet.add(res.getValue());
            }

        }
        info.setRoles(roleSet);

        //获取用户权限
        info.setStringPermissions(resSet);
        return info;
    }

    //用户认证 当调用Subject currentUser = SecurityUtils.getSubject();调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", username);
        //密码进行加密处理

        String pawDES = Md5Encrypt.md5(password);
        map.put("pswd", pawDES);

        User user = userService.login(username,password);

        /*if(user==null){
            throw new UnknownAccountException("用户名或密码错误！");
        }*/

        return new SimpleAuthenticationInfo(user, pawDES, getName());
    }
}