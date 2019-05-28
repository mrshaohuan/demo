package com.ty.web.action;

import com.ty.core.service.IUserService;
import com.ty.core.vo.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class UserControllor {

    @Autowired
    IUserService userService;


    @RequestMapping("/admin/userList.htm")
    public ModelAndView userList(String userName,String password){
        ModelAndView mv = new ModelAndView("/index.html");
        User user = new User();
        user.setAddTime(new Date());
        user.setUserName("123");
        user.setPassword("32123");

        userService.insert(user);
        System.out.println("123123");
        return mv;
    }


    @RequestMapping("/admin/login.htm")
    public String login(Model model,HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进来啦");
        System.out.println("访问静态资源。。。");
        model.addAttribute("1",2);
        return "login";
    }


    @RequestMapping("/admin/loginUser.htm")
    public String loginUser(Model model, String userName, String password,HttpSession session) {
        try {
            //授权认证
            UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(userName,password);
            Subject subject = SecurityUtils.getSubject();
            //完成登录
            subject.login(usernamePasswordToken);
            //获得用户对象
            User user=(User) subject.getPrincipal();

        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("msg", "密码错误");
            return "login";
        } catch (LockedAccountException e) {
            model.addAttribute("msg", "登录失败，该用户已被冻结");
            return "login";
        } catch (AuthenticationException e) {
            model.addAttribute("msg", "该用户不存在");
            return "login";
        } catch (Exception e) {
            model.addAttribute("msg","系统错误轻联系管理员！");
            return "login";
        }
        model.addAttribute("msg","登陆成功！");
        return "layout/index";

    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//        session.removeAttribute("user");
        return "login";
    }

}
