package com.online.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.mapper.TbSpecificationMapper;
import com.online.shopping.mapper.TbSpecificationOptionMapper;
import com.online.shopping.pojo.TbSpecification;
import com.online.shopping.pojo.TbSpecificationExample;
import com.online.shopping.pojo.TbSpecificationOption;
import com.online.shopping.pojo.TbSpecificationOptionExample;
import com.online.shopping.pojogroup.Specification;
import com.online.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private TbSpecificationMapper mapper;
    @Autowired
    private TbSpecificationOptionMapper optionMapper;

    @Override
    public List<TbSpecification> findAll() {
        //PageResult pageResult = new PageResult();
        List<TbSpecification> list = mapper.selectByExample(null);
        return list;
    }



    @Override
    public PageResult findPage(int page, int rows,TbSpecification searchEntity) {
        PageResult result = new PageResult();
        PageHelper.startPage(page,rows);
        String searchName = searchEntity.getSpecName();//获得得到的名字
        //System.out.println("searchName-----"+searchName);
        TbSpecificationExample example = new TbSpecificationExample();
        List<TbSpecification> list = new ArrayList<>() ;
        if(searchName!=null&&!searchName.equals("")) {
            TbSpecificationExample.Criteria criteria = example.createCriteria();
            criteria.andSpecNameLike("%" + searchName + "%");
            list = mapper.selectByExample(example);
        }else {
            list = mapper.selectByExample(null);
        }
        PageInfo<TbSpecification> info = new PageInfo<>(list);
        result.setTotal(info.getTotal());
        result.setRows(info.getList());
        return result;
    }

    @Override
    public Result delete(long[] ids) {
        Result result = new Result();
        try {
            for (long id : ids) {
                mapper.deleteByPrimaryKey(id);
            }
            result.setSuccess(true);
            result.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("删除失败");
        }
        return result;
    }

    @Override
    public Specification findOne(long id) {
        Specification specification = new Specification();
        TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(id);

        TbSpecification tbSpecification = mapper.selectByPrimaryKey(id);

        List<TbSpecificationOption> tbSpecificationOptions = optionMapper.selectByExample(tbSpecificationOptionExample);

        specification.setSpecification(tbSpecification);
        specification.setSpecificationOptionList(tbSpecificationOptions);
        return specification;
    }

    @Override
    public HashMap<Object, Object> add(Specification entity) {
        TbSpecification tbSpecification = entity.getSpecification();
        List<TbSpecificationOption> optionList = entity.getSpecificationOptionList();
        HashMap<Object, Object> map = new HashMap<>();
        try {
            mapper.insert(tbSpecification);
            for (TbSpecificationOption option : optionList) {
                //他那边不自动赋值所以要手动绑定
                option.setSpecId(tbSpecification.getId());
                optionMapper.insert(option);
            }
            map.put("success",true);
            map.put("message","添加成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","添加失败");
        }
        return map;
    }

    @Override
    public HashMap<Object, Object> update(Specification specification) {
        TbSpecification tbSpecification = specification.getSpecification();
        List<TbSpecificationOption> optionList = specification.getSpecificationOptionList();
        HashMap<Object, Object> map = new HashMap<Object, Object>();
        try {
            mapper.updateByPrimaryKey(tbSpecification);
            for (TbSpecificationOption option : optionList) {
                option.setSpecId(tbSpecification.getId());
                optionMapper.updateByPrimaryKey(option);
            }
            map.put("success",true);
            map.put("message","修改成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","修改失败");
        }
        return map;
    }

    @Override
    public List<Map> selectOptionList() {
        List<Map> maps = mapper.selectOptionList();
        return maps;
    }
}
