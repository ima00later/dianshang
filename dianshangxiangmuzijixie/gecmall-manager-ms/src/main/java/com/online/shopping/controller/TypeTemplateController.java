package com.online.shopping.controller;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbSpecification;
import com.online.shopping.pojo.TbTypeTemplate;
import com.online.shopping.service.BrandService;
import com.online.shopping.service.SpecificationService;
import com.online.shopping.service.TbTypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/typeTemplate-Ms")
public class TypeTemplateController {
    @Autowired
    private TbTypeTemplateService typeTemplateService;
    @Autowired
    private SpecificationService specificationService;
    @Autowired
    private BrandService brandService;

    @RequestMapping("/search")
    public PageResult search(int page, int rows, @RequestBody TbTypeTemplate searchEntity){
        PageResult result=typeTemplateService.search(page,rows,searchEntity);
        //System.out.println("searchEntityname========"+searchEntity.getName());
        return result;
    }

    @RequestMapping("/delete")
    public Result delete(long[] ids){
        Result result=typeTemplateService.delete(ids);
        return result;
    }


   /* 有一个方法是在页面中得到规格选项卡的，方法在SpecificationController，SpecificationService中
     的selectOptionList*/

   /* 而另外一个方法是在页面中得到品牌选项卡的，方法在BrandController，BrandService中
     的selectOptionList*/

   //得到两种选项卡后，就可以实现添加和修改s了
   @RequestMapping("/add")
   public Result add(@RequestBody TbTypeTemplate entity){
       Result result=typeTemplateService.add(entity);
       return result;
   }

   @RequestMapping("/update")
    public Result update(@RequestBody TbTypeTemplate entity){
       Result result=typeTemplateService.update(entity);
       return result;
   }

   @RequestMapping("/findOne")
    public TbTypeTemplate findOne(long id){
       TbTypeTemplate template=typeTemplateService.findOne(id);
       return template;
   }

   @RequestMapping("/findBySpecList")
    public List<Map> findBySpecList(long id){
       List<Map> specList = typeTemplateService.findBySpecList(id);
       for(Map map:specList){
           System.out.println(map.get("id")+"========="+map.get("text"));
       }
       return specList;
   }

   //@RequestMapping("/")
}
