package com.online.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.shopping.entity.PageResult;
import com.online.shopping.mapper.TbBrandMapper;
import com.online.shopping.pojo.TbBrand;
import com.online.shopping.pojo.TbBrandExample;
import com.online.shopping.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper mapper;

    @Override
    public List<TbBrand> findAll() {
        return mapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageResult pageResult = new PageResult();
        List<TbBrand> brandsAll = mapper.selectByExample(null);
        PageInfo<TbBrand> info = new PageInfo<>(brandsAll);
        pageResult.setRows(info.getList());
        pageResult.setTotal(info.getTotal());
        return pageResult;
    }

    @Override
    public void addBrand(TbBrand brand) {
        mapper.insert(brand);
    }

    @Override
    public void updateBrand(TbBrand brand) {
        mapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(long[] ids) {
        for(long id:ids){
        mapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public TbBrand findBrandDetail(long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map> selectOptionList() {
        List<Map> maps = mapper.selectOptionList();
        return maps;
    }
}
