package com.online.shopping.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    public List<TbBrand> findAll();
    public PageResult findPage(int pageNum, int pageSize);

    void addBrand(TbBrand brand);

    void updateBrand(TbBrand brand);

    void delete(long[] ids);

    TbBrand findBrandDetail(long id);

    List<Map> selectOptionList();
}
