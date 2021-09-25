package com.online.shopping.service.impl;

import com.online.shopping.mapper.TbItemCatMapper;
import com.online.shopping.pojo.TbItemCat;
import com.online.shopping.pojo.TbItemCatExample;
import com.online.shopping.service.ItemCatgoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ItemCatgoryServiceImpl implements ItemCatgoryService {
    @Autowired
    private TbItemCatMapper mapper;
    @Override
    public List<TbItemCat> findByParentId(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = mapper.selectByExample(example);
        return tbItemCats;
    }

    @Override
    public TbItemCat findOne(long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public HashMap<Object, Object> update(TbItemCat itemCat) {
        HashMap<Object, Object> map = new HashMap<>();
        try{
            mapper.updateByPrimaryKey(itemCat);
            map.put("flag",true);
            map.put("message","修改目录成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("flag",false);
            map.put("message","修改目录失败");
        }

        return map;
    }

    @Override
    public HashMap<Object, Object> add(TbItemCat itemCat) {
        HashMap<Object,Object>map=new HashMap<>();
        try {
            mapper.insert(itemCat);
            map.put("flag",true);
            map.put("message","添加成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("flag",false);
            map.put("message","添加失败");
        }
        return map;
    }

    @Override
    public TbItemCat findParent(long id) {
        TbItemCat one = findOne(id);//调用同类方法
        TbItemCat itemCatParent = mapper.selectByPrimaryKey(one.getParentId());
        return itemCatParent;
    }

}
