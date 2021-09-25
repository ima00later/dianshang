package com.shopping.search.controller;

import com.shopping.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/itemsearch-ms")
public class ItemSearchController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/search")
    public HashMap<String,Object> search(@RequestBody HashMap<String,String>searchMap){
        String keywords = searchMap.get("keywords");//用map可以自动映射
        HashMap<String,Object> resultMap=itemService.search(keywords);
        return resultMap;
    }
}
