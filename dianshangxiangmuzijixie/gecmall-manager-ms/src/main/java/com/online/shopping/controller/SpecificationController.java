package com.online.shopping.controller;

import com.netflix.discovery.converters.Auto;
import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbSpecification;
import com.online.shopping.pojogroup.Specification;
import com.online.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification-Ms")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;
    @RequestMapping("/findAll")
    public List<TbSpecification> findAll(){
        return specificationService.findAll();
    }

    @RequestMapping(value = "/search")
    public PageResult findPage(int page,int rows,@RequestBody TbSpecification searchEntity){
        PageResult result=specificationService.findPage(page,rows,searchEntity);
        return result;
    }


    @RequestMapping("/delete")
    public Result delete(long[] ids){
        Result result = specificationService.delete(ids);
        return result;
    }

     @RequestMapping(value = "/add")
    public  HashMap<Object, Object> add(@RequestBody Specification entity){
        //试试用map
        HashMap<Object, Object> map=specificationService.add(entity);
        return map;
    }


    //前端要修改，得先找到这个东西
    @RequestMapping("/findOne")
    public Specification findOne(long id){
        Specification specification=specificationService.findOne(id);
        return specification;
    }

    @RequestMapping("/update")
    public HashMap<Object,Object> update(@RequestBody Specification specification){
        HashMap<Object,Object> map=specificationService.update(specification);
        return map;
    }

    //这个是通过tbtemplate找到对应的specification的返回成一个map
    // 其中的key值为{id:"",text:}的json字符串,返回给select2
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        List<Map> maps=specificationService.selectOptionList();
        return maps;
    }
}
