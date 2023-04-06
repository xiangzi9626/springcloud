package com.example.feign.clients.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("goods")
public interface GoodsClient {
    @RequestMapping("/admin/goods/update_category")
    Object updateCategory(@RequestParam("cid") Integer cid);
}
