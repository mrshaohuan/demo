package com.ty.core.service;

import com.ty.core.vo.Res;
import com.ty.core.vo.Role;
import com.ty.util.PageBean;

import java.util.List;

public interface IRoleService {

    int insert(Role role);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role role);

    Role selectByRoleId(long id);

    List<Role> findRolesByUid(long uid);

    PageBean findRolePage(int pageSize, int page);
}
