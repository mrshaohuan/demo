package com.ty.core.service;

import com.ty.core.dao.ResMapper;
import com.ty.core.vo.Res;
import com.ty.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class ResService implements IResService{

    @Autowired
    private ResMapper resMapper;
    public int insert(Res res){
        return resMapper.insert(res);
    }

    public int deleteByPrimaryKey(Long id){
        return resMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Res res){
        return resMapper.updateByPrimaryKeySelective(res);
    }

    public Res selectByResId(long id){
        return resMapper.selectByPrimaryKey(id);
    }

    public PageBean findRolePage(int pageSize, int page){
        final int length = pageSize;
        final int currentPage = PageBean.countCurrentPage(page);
        List<Res> users = resMapper.findUserPage(length, currentPage);
        int count = resMapper.findUserCount();

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
