package com.online.shopping.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbSpecification;
import com.online.shopping.pojo.TbTypeTemplate;

import java.util.List;
import java.util.Map;

public interface TbTypeTemplateService {
    PageResult search(int page, int rows, TbTypeTemplate searchEntity);

    Result delete(long[] ids);

    Result add(TbTypeTemplate entity);

    Result update(TbTypeTemplate entity);

    TbTypeTemplate findOne(long id);

    List<Map> findBySpecList(long id);
}
