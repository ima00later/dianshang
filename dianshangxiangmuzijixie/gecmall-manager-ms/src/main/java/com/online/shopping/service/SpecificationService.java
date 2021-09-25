package com.online.shopping.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbSpecification;
import com.online.shopping.pojogroup.Specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SpecificationService {
    List<TbSpecification> findAll();

    PageResult findPage(int page, int rows,TbSpecification searchEntity);

    Result delete(long[] ids);

    Specification findOne(long id);

    HashMap<Object, Object> add(Specification entity);

    HashMap<Object, Object> update(Specification specification);

    List<Map> selectOptionList();
}
