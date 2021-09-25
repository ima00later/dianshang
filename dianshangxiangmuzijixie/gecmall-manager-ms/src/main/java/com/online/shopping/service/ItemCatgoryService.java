package com.online.shopping.service;

import com.online.shopping.pojo.TbItemCat;

import java.util.HashMap;
import java.util.List;

public interface ItemCatgoryService {
    List<TbItemCat> findByParentId(long parentId);

    TbItemCat findOne(long id);

    HashMap<Object, Object> update(TbItemCat itemCat);

    HashMap<Object, Object> add(TbItemCat itemCat);

    TbItemCat findParent(long id);
}
