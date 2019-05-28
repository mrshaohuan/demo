package com.ty.core.service;

import com.ty.core.dao.RoleMapper;
import com.ty.core.vo.Role;
import com.ty.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService implements IRoleService{

    @Autowired
    private RoleMapper roleMapper;

    public int insert(Role role){
        return roleMapper.insert(role);
    }

    public int deleteByPrimaryKey(Long id){
        return roleMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Role role){
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public Role selectByRoleId(long id){
        return roleMapper.selectByPrimaryKey(id);
    }

    public List<Role> findRolesByUid(long uid){
        return roleMapper.selectByUserId(uid);
    }

    public PageBean findRolePage(int pageSize, int page){
        final int length = pageSize;
        final int currentPage = PageBean.countCurrentPage(page);
        List<Role> roles = roleMapper.findRolePage(length, currentPage);
        int count = roleMapper.findRoleCount();

        int totalPage = PageBean.countTotalPage(pageSize, count);
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(roles);
        pageBean.init();

        return pageBean;
    }
}
