package com.shopping.content.controller;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbContentCategory;
import com.shopping.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contentCategory-ms")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;
    @RequestMapping("/search")
    public PageResult findPage(int page, int rows){
        PageResult result = contentCategoryService.search(page, rows);
        return result;
    }
    @RequestMapping("/findOne")
    public TbContentCategory findOne(long id){
        TbContentCategory catory = contentCategoryService.findOne(id);

        return catory;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody TbContentCategory entity){
        Result result=contentCategoryService.add(entity);
        return  result;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody TbContentCategory entity){
        Result result=contentCategoryService.update(entity);
        return  result;
    }

    @RequestMapping("/delete")
    public Result delete(long[] ids){
        Result result=contentCategoryService.delete(ids);
        return  result;
    }

    @RequestMapping("/findAll")//用于回显数据给content页面
    public List<TbContentCategory> findAll(){
        List<TbContentCategory> list= contentCategoryService.findAll();
        return list;
    }
}
