package com.shopping.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbContentCategory;
import com.online.shopping.pojo.TbContentCategoryExample;
import com.shopping.content.mapper.TbContentCategoryMapper;
import com.shopping.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper mapper;
    @Override
    public PageResult search(int page, int rows) {
        PageHelper.startPage(page,rows);
        PageResult pageResult = new PageResult();


        List<TbContentCategory> list = mapper.selectByExample(null);
        PageInfo<TbContentCategory> info = new PageInfo<>(list);
        pageResult.setTotal(info.getTotal());
        pageResult.setRows(info.getList());
        return pageResult;
    }

    @Override
    public TbContentCategory findOne(long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Result add(TbContentCategory entity) {
        Result result = new Result();
        try{
            mapper.insert(entity);
            result.setSuccess(true);
            result.setMessage("添加成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("添加失败");
        }
        return result;
    }

    @Override
    public Result update(TbContentCategory entity) {
        Result result = new Result();
        try{
            mapper.updateByPrimaryKey(entity);
            result.setMessage("修改成功");
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("修改失败");
        }

        return null;
    }

    @Override
    public Result delete(long[] ids) {
        Result result = new Result();
        try {
            for(long id:ids){
                mapper.deleteByPrimaryKey(id);
            }
            result.setMessage("删除成功");
            result.setSuccess(true);
        }catch (Exception e){
            result.setMessage("删除失败");
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public List<TbContentCategory> findAll() {
        return mapper.selectByExample(null);
    }
}
