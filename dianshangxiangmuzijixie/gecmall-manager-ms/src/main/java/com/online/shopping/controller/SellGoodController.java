package com.online.shopping.controller;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbBrand;
import com.online.shopping.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand-Ms")
public class SellGoodController {
    @Autowired
    private BrandService brandService;
    @RequestMapping("/findAll")
    public List findAll(){
        return brandService.findAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum, int pageSize){
        return brandService.findPage(pageNum,pageSize);
    }

    @RequestMapping("/add")
    public Result addBrand(@RequestBody TbBrand brand){
        Result result = new Result();
        try{
            brandService.addBrand(brand);
            result.setMessage("添加品牌成功");
            result.setSuccess(true);
        }catch (Exception e){
            result.setMessage("添加品牌失败");
            result.setSuccess(false);
        }
        return result;
    }
    @RequestMapping("/findBrandDetail")
    public TbBrand findBrandDetail(long id){
        return brandService.findBrandDetail(id);
    }


    @RequestMapping("/updateBrand")
    public Result updateBrand(@RequestBody TbBrand brand){
        Result result = new Result();
        try {
            brandService.updateBrand(brand);
            result.setSuccess(true);
            result.setMessage("修改品牌成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("修改品牌失败");
        }
        return result;
    }

    @RequestMapping("/delete")
    public Result delete(long[] ids){
        Result result = new Result();
        try{
            brandService.delete(ids);
            result.setMessage("删除成功");
            result.setSuccess(true);
        }catch (Exception e){
            result.setMessage("删除失败");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        List<Map> maps=brandService.selectOptionList();
        return maps;
    }
}
