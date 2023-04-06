package com.example.feign.clients.admin;

import com.example.commons.entity.GoodsCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@FeignClient("goodscategory")
public interface GoodsCategoryClient {
    @RequestMapping("/admin/goods/category/all")
    List<GoodsCategory> goodsCategoryList();
}
