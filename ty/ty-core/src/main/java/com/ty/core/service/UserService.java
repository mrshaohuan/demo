package com.ty.core.service;

import com.ty.core.dao.UserMapper;
import com.ty.core.vo.User;
import com.ty.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User login(String userName, String password) {
        return userMapper.login(userName,password);
    }

    @Override
    public User findUserByName(String userName){
        return userMapper.findUserByName(userName);
    }

    @Override
    public PageBean findUserPage(int pageSize, int page){
        final int length = pageSize;
        final int currentPage = PageBean.countCurrentPage(page);
        List<User> users = userMapper.findUserPage(length, currentPage);
        int count = userMapper.findUserCount();

        int totalPage = PageBean.countTotalPage(pageSize, count);
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(users);
        pageBean.init();
        return pageBean;
    }

}
