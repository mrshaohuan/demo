package com.ty.web.common;

import com.ty.util.CommUtil;
import com.ty.util.HttpInclude;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    @ModelAttribute
    public void populateModel(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("CommUtil", new CommUtil());
        String webPath = CommUtil.getURL(request);
        CommUtil.null2Int(Integer.valueOf(request.getServerPort()));
        model.addAttribute("domainPath", CommUtil.generic_domain(request));
        model.addAttribute("webPath", webPath);

        //super.addObject("user", SecurityUserHolder.getCurrentUser());
        model.addAttribute("httpInclude", new HttpInclude(request, response));
        String query_url = "";
        if ((request.getQueryString() != null) &&
                (!request.getQueryString().equals(""))) {
            query_url = "?" + request.getQueryString();
        }
        model.addAttribute("current_url", request.getRequestURI() + query_url);
    }

}
