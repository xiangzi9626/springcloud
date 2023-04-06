package com.example.goodscategory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commons.entity.Goods;
import com.example.commons.entity.GoodsCategory;
import com.example.feign.clients.admin.GoodsClient;
import com.example.goodscategory.mapper.GoodsCategoryMapper;
import com.example.goodscategory.service.IGoodsCategoryService;
import feign.FeignException;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 丁祥 QQ 2421341497
 * @since 2022-11-15
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements IGoodsCategoryService {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    private GoodsClient goodsClient;
    private Goods goods;

    @Override
    @GlobalTransactional
    public void delete(Integer id) {
         goodsCategoryMapper.deleteById(id);
        try {
            goodsClient.updateCategory(id);
        } catch (FeignException e) {
            e.printStackTrace();
            throw new RuntimeException(e.contentUTF8(),e);
        }
    }
}
