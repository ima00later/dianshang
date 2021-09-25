package com.shopping.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbContent;
import com.online.shopping.pojo.TbContentExample;
import com.shopping.content.mapper.TbContentMapper;
import com.shopping.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageResult search(int page, int rows) {
        PageResult result = new PageResult();
        PageHelper.startPage(page,rows);

        PageInfo info=new PageInfo(mapper.selectByExample(null));
        result.setRows(info.getList());
        result.setTotal(info.getTotal());
        return result;
    }

    @Override
    public TbContent findOne(long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Result delete(long[] ids) {
        Result result = new Result();
        try{
            for(long id:ids){
                mapper.deleteByPrimaryKey(id);
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("删除失败");
        }
        return result;
    }

    @Override
    public Result add(TbContent entity) {
        Result result = new Result();
        try{
            String pic = entity.getPic();

            mapper.insert(entity);

            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("添加失败");
        }
        return result;
    }

    @Override
    public Result update(TbContent entity) {
        Result result = new Result();
        try{
            mapper.updateByPrimaryKey(entity);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("修改失败");
        }
        return result;
    }

    @Override
    public List<TbContent> findByCategoryId(long categoryId) {
        List<TbContent> categoryList = (List<TbContent>)redisTemplate.boundValueOps("categoryList").get();
        System.out.println("categoryList=====\n"+categoryList);
        if(categoryList!=null&&categoryList.size()>0){
            return categoryList;//如果有说明redis缓存有数据，存了新图片后要删除缓存再重新存一次
        }else{
            //否则说明redis缓存没有数据
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(categoryId);
            List<TbContent> contents = mapper.selectByExample(example);

            redisTemplate.boundValueOps("categoryList").set(contents);
            return contents;
        }

        //return null;
    }
}
