package com.ty.core.service;

import com.ty.core.vo.Res;
import com.ty.util.PageBean;

import java.util.List;

public interface IResService {

    int insert(Res res);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Res res);

    Res selectByResId(long id);

    PageBean findRolePage(int pageSize, int page);

}
