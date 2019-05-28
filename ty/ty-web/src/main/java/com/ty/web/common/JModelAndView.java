package com.ty.web.common;

import com.ty.util.CommUtil;
import com.ty.util.HttpInclude;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JModelAndView extends ModelAndView {

    public JModelAndView(String viewName,HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("CommUtil", new CommUtil());
        String webPath = CommUtil.getURL(request);
        CommUtil.null2Int(Integer.valueOf(request.getServerPort()));
        request.setAttribute("domainPath", CommUtil.generic_domain(request));
        request.setAttribute("webPath", webPath);

        //super.addObject("user", SecurityUserHolder.getCurrentUser());
        request.setAttribute("httpInclude", new HttpInclude(request, response));
        String query_url = "";
        if ((request.getQueryString() != null) &&
                (!request.getQueryString().equals(""))) {
            query_url = "?" + request.getQueryString();
        }
        request.setAttribute("current_url", request.getRequestURI() + query_url);
    }
}
