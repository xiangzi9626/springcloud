package com.example.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commons.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 丁祥 QQ 2421341497
 * @since 2022-11-14
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Map<String,Object>> goodsList(@Param("limit1") int limit1, @Param("limit2") int limit2);
}
