package com.ty.web.action;

import com.ty.core.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class IndexControllor {

    @RequestMapping("/admin/index.htm")
    public String index(String userName, String password){

        return "layout/index";
    }


    @RequestMapping("/admin/nav.htm")
    public String nav(String userName, String password){

        return "layout/nav";
    }


    @RequestMapping("/admin/title.htm")
    public String title(String userName, String password){

        return "layout/title";
    }


    @RequestMapping("/admin/common.htm")
    public String common(String userName, String password){

        return "layout/common";
    }

    @RequestMapping("/admin/wellcome.htm")
    public String wellcome(String userName, String password){

        return "layout/wellcome";
    }

}
