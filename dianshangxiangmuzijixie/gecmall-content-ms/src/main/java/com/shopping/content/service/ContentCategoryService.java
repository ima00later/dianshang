package com.shopping.content.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService {
    PageResult search(int page, int rows);

    TbContentCategory findOne(long id);

    Result add(TbContentCategory entity);

    Result update(TbContentCategory entity);

    Result delete(long[] ids);

    List<TbContentCategory> findAll();
}
