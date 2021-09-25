package com.shopping.content.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbContent;

import java.util.List;

public interface ContentService {
    PageResult search(int page, int rows);

    TbContent findOne(long id);

    Result delete(long[] ids);

    Result add(TbContent entity);

    Result update(TbContent entity);

    List<TbContent> findByCategoryId(long categoryId);
}
