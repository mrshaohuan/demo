package com.ty.core.service;

import com.ty.core.vo.User;
import com.ty.util.PageBean;

public interface IUserService {

    int insert(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int deleteByPrimaryKey(Long id);

    User login(String userName,String password);

    User findUserByName(String userName);

    PageBean findUserPage(int pageSize, int page);
}
