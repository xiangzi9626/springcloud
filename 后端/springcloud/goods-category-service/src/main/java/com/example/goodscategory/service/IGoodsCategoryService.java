package com.example.goodscategory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commons.entity.GoodsCategory;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 丁祥 QQ 2421341497
 * @since 2022-11-15
 */
public interface IGoodsCategoryService extends IService<GoodsCategory> {
    void delete(Integer id);
}
