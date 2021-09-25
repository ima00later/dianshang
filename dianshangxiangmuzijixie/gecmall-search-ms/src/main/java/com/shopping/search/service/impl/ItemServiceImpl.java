package com.shopping.search.service.impl;

import com.online.shopping.pojo.TbItem;
import com.shopping.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private SolrTemplate solrTemplate;

    @Value("${spring.data.solr.collection}")
    private String collection;

    @Override
    public HashMap<String, Object> search(String keywords) {

        HashMap<String, Object> map = new HashMap<>();

        //这一步忘记加了
        Criteria criteria = new Criteria("item_title").contains(keywords);
        Query query = new SimpleQuery("*:*");
        query.setRows(100);//放100条数据
        query.addCriteria(criteria);
        Page<TbItem> tbItemPage = solrTemplate.query(collection, query, TbItem.class);
        List<TbItem> content = tbItemPage.getContent();
        map.put("rows",content);
        return map;
    }
}
