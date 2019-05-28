package com.ty.auth.controller;

import com.alibaba.fastjson.JSON;
import com.ty.core.service.IResService;
import com.ty.util.PageBean;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
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
public class ResController {
    @Autowired
    private IResService resService;

    @RequestMapping("/auth/json/findRes.htm")
    public String findUser(HttpServletRequest request, HttpServletResponse response, String currentPage){
        int page = 1;
        if (StringUtils.isNotBlank(currentPage)) {
            page = Integer.valueOf(currentPage);
        }
        PageBean pageBean = resService.findRolePage(15,page);
        List<Map> list = new ArrayList();
        Map<String,Object> map = new LinkedHashMap();
        map.put("currentPage", pageBean.getCurrentPage());
        map.put("totalPage", pageBean.getTotalPage());
        map.put("pageSize", pageBean.getPageSize());
        map.put("allRow", pageBean.getAllRow());
        map.put("data", JSONArray.fromObject(pageBean.getList()));
        list.add(map);
        return JSON.toJSONString(list);
    }


}
