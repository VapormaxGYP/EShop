package com.vapor.eshop.service;

import com.vapor.eshop.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vapor.eshop.entity.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    public Result<?> listCategoryInTree();
}
