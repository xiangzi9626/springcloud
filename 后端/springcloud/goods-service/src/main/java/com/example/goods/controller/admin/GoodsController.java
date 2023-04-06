package com.example.goods.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.commons.entity.Goods;
import com.example.commons.entity.GoodsCategory;
import com.example.commons.utils.Result;
import com.example.feign.clients.admin.GoodsCategoryClient;
import com.example.goods.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 丁祥 QQ 2421341497
 * @since 2022-11-14
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsController {
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private GoodsCategoryClient goodsCategoryClient;
    @RequestMapping("/batch_delete")
    public Map<String, Object> batchDelete(@RequestBody List<Integer> ids) {
        boolean batch = goodsService.removeBatchByIds(ids);
        if (batch) {
            return Result.success("批量删除成功");
        } else {
            return Result.error("删除失败请重试");
        }
    }

    @RequestMapping("/get")
    public Goods getGoodsById(@RequestParam Integer id) {
        Goods goods = goodsService.getById(id);
        return goods;
    }

    @RequestMapping("/add")
    public Map<String, Object> addGoods(@RequestBody Goods goods) {
        boolean save = goodsService.save(goods);
        if (save) {
            return Result.success("");
        } else {
            return Result.error("提交失败请重试");
        }
    }

    @RequestMapping("/edit")
    public Map<String, Object> updateGoods(@RequestBody Goods goods) {
        boolean update = goodsService.updateById(goods);
        if (update) {
            return Result.success("更新成功");
        } else {
            return Result.error("提交失败请重试");
        }
    }

    @RequestMapping("/list")
    public Map<String, Object> goodsList(@RequestBody Map<String, Integer> param) {
        int currentPage = param.get("currentPage");
        int pageSize = param.get("pageSize");
        long total = goodsService.count();
        List<Map<String,Object>> goodsList = goodsService.goodsList((currentPage - 1) * pageSize, pageSize);
        List<GoodsCategory> goodsCategoryList=goodsCategoryClient.goodsCategoryList();
        for (int i=0;i<goodsList.size();i++){
            for (int j=0;j<goodsCategoryList.size();j++){
                String goodsCid=goodsList.get(i).get("cid")+"";
                String categoryId=goodsCategoryList.get(j).getCategoryId()+"";
                if (goodsCid.equals(categoryId)){
                    goodsList.get(i).put("name",goodsCategoryList.get(j).getName());
                    break;
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", goodsList);
        return map;
    }


    @RequestMapping("/delete")
    public Map<String, Object> deleteGoods(@RequestBody Map<String, Integer> param) {
        int id = param.get("id");
        boolean remove = goodsService.removeById(id);
        if (remove) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败请重试");
        }
    }
    @RequestMapping("/update_category")
    public void updateCategory(@RequestParam("cid") Integer cid){
        System.out.println(cid);
        goodsService.updateCategory(cid);
    }
}
