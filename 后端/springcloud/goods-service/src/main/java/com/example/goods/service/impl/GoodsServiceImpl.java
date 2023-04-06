package com.example.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commons.entity.Goods;
import com.example.goods.mapper.GoodsMapper;
import com.example.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 丁祥 QQ 2421341497
 * @since 2022-11-14
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    private Goods goods;

    @Override
    @Transactional
    public void updateCategory(Integer cid) {
         goodsMapper.update(goods, new UpdateWrapper<Goods>().eq("cid", cid).set("cid", ""));
    }

    @Override
    public List<Map<String, Object>> goodsList(int limit1, int limit2) {
        return goodsMapper.goodsList(limit1, limit2);
    }
}
