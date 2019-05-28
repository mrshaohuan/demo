package com.ty.web.common;

import com.ty.util.CommUtil;
import com.ty.util.HttpInclude;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {

        httpServletRequest.setAttribute("CommUtil", new CommUtil());
        String webPath = CommUtil.getURL(httpServletRequest);
        httpServletRequest.setAttribute("webPath", webPath);
        httpServletRequest.setAttribute("httpInclude", new HttpInclude(httpServletRequest, httpServletResponse));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
