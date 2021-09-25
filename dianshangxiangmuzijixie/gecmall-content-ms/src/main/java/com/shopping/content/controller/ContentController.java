package com.shopping.content.controller;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbContent;
import com.shopping.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content-ms")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/search")
    public PageResult search(int page,int rows){
        PageResult result=contentService.search(page,rows);
        return result;
    }

    @RequestMapping("/findOne")
    public TbContent findOne(long id){
        TbContent content =contentService.findOne(id);
        return content;
    }

    @RequestMapping("/delete")
    public Result delete(long[] ids){
        Result result = contentService.delete(ids);
        return result;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody TbContent entity){
        Result result=contentService.add(entity);
        return result;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody TbContent entity){
        Result result = contentService.update(entity);
        return  result;
    }
    @RequestMapping("/findByCategoryId")
    public List<TbContent> findByCategoryId(long categoryId){
        List<TbContent> list=contentService.findByCategoryId(categoryId);
        return list;
    }
}
