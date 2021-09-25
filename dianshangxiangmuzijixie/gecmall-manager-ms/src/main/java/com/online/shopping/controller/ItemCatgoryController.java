package com.online.shopping.controller;

import com.online.shopping.pojo.TbItemCat;
import com.online.shopping.service.ItemCatgoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/itemCat-ms")
public class ItemCatgoryController {
    @Autowired
    private ItemCatgoryService service;
    @RequestMapping("/findByParentId")
    public List<TbItemCat> findByParentId(long parentId){
        return service.findByParentId(parentId);
    }

    //查询后返回entity对象，然后再根据对象进行修改
    @RequestMapping("/findOne")
    public TbItemCat findOne(long id){
        //System.out.println(id);
        TbItemCat itemCat=service.findOne(id);
        return itemCat;
    }

    @RequestMapping("/update")
    public HashMap<Object,Object> update(@RequestBody TbItemCat itemCat){
        HashMap<Object,Object> map=service.update(itemCat);
        return map;
    }

    @RequestMapping("/add")
    public HashMap<Object,Object> add(@RequestBody TbItemCat itemCat){
        HashMap<Object,Object> map=service.add(itemCat);
        return map;
    }

    //添加，返回上级并且能在页面中显示
    @RequestMapping("/findParent")
    public TbItemCat findParent(long id){
        TbItemCat itemCatParent = service.findParent(id);
        return itemCatParent;
    }


}
