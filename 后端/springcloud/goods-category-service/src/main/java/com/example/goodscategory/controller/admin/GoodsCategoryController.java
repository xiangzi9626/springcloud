package com.example.goodscategory.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commons.entity.GoodsCategory;
import com.example.commons.utils.Result;
import com.example.goodscategory.service.impl.GoodsCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 丁祥 QQ 2421341497
 * @since 2022-11-15
 */
@RestController
@RequestMapping("/admin/goods/category")
public class GoodsCategoryController {
    @Autowired
    private GoodsCategoryServiceImpl goodsCategoryService;

    @RequestMapping("/list")
    public List<GoodsCategory> goodsCategory() {
        List<GoodsCategory> goodsCategoryList = goodsCategoryService.list();
        return buildTree(goodsCategoryList);
    }

    @RequestMapping("/all")
    public List<GoodsCategory> goodsCategoryList() {
        List<GoodsCategory> goodsCategoryList = goodsCategoryService.list();
        return goodsCategoryList;
    }

    public List<GoodsCategory> buildTree(List<GoodsCategory> list) {
        List<GoodsCategory> resultList = new ArrayList<>();
        for (GoodsCategory goodsCategory : list) {
            // 寻找子节点
            for (GoodsCategory g : list) {
                if (g.getPid().equals(goodsCategory.getCategoryId())) {
                    goodsCategory.getChildren().add(g);
                }
            }
            if (goodsCategory.getPid() == 0) {
                resultList.add(goodsCategory);
            }
        }
        return resultList;
    }

    @RequestMapping("/add")
    public Map<String, Object> addGoodsCategory(@RequestBody GoodsCategory goodsCategory) {
        boolean save = goodsCategoryService.save(goodsCategory);
        if (save) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败请重试");
        }
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam("id") Integer id) {
        long count = goodsCategoryService.count(new QueryWrapper<GoodsCategory>().eq("pid", id));
        if (count > 0) {
            return Result.error("请先删除子分类");
        }
        try {
            goodsCategoryService.delete(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败请重试");
        }
    }
}
