package com.online.shopping.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.mapper.TbSpecificationOptionMapper;
import com.online.shopping.mapper.TbTypeTemplateMapper;
import com.online.shopping.pojo.*;
import com.online.shopping.service.TbTypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TbTypeTemplateServiceImpl implements TbTypeTemplateService {
    @Autowired
    private TbTypeTemplateMapper mapper;
    @Autowired
    private TbSpecificationOptionMapper optionMapper;

    @Override
    public PageResult search(int page, int rows, TbTypeTemplate searchEntity) {
        PageResult result = new PageResult();
        PageHelper.startPage(page,rows);
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();
        if(searchEntity.getName()!=null&&!searchEntity.getName().equals("")){
            criteria.andNameLike("%"+searchEntity.getName()+"%");
        }
        List<TbTypeTemplate> tbTypeTemplates = mapper.selectByExample(example);
        PageInfo<TbTypeTemplate> info = new PageInfo<>(tbTypeTemplates);
        result.setRows(info.getList());
        result.setTotal(info.getTotal());
        return result;
    }

    @Override
    public Result delete(long[] ids) {
        Result result = new Result();
        try {
            for (long id : ids) {
                mapper.deleteByPrimaryKey(id);
            }
            result.setMessage("删除成功");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("删除失败");
        }
        return result;
    }

    @Override
    public Result add(TbTypeTemplate entity) {
        Result result = new Result();
        try {

            mapper.insert(entity);
            result.setMessage("添加成功");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("添加失败");
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public Result update(TbTypeTemplate entity) {
        Result result = new Result();
        try {
            mapper.updateByPrimaryKey(entity);
            result.setSuccess(true);
            result.setMessage("修改模板成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("修改模板失败");
        }
        return result;
    }

    @Override
    public TbTypeTemplate findOne(long id) {

        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map>findBySpecList(long id) {
        TbTypeTemplate template = mapper.selectByPrimaryKey(id);
        String specIds = template.getSpecIds();
        //List<TbSpecification> list = new ArrayList<>();
        ObjectMapper mapperObject = new ObjectMapper();
        //转化为json再放入
        JavaType javaType = mapperObject.getTypeFactory().constructParametricType(List.class, Map.class);
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        try {
            List<Map> tbSpecifications = (List<Map>) mapperObject.readValue(specIds, javaType);
            for(Map map:tbSpecifications){
                Integer i = (Integer) map.get("id");//转成Integer再转成long
                example.createCriteria().andSpecIdEqualTo(i.longValue());
                List<TbSpecificationOption> options = optionMapper.selectByExample(example);
                map.put("options",options);
            }
            return tbSpecifications;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
