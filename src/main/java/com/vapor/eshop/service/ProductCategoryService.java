package com.vapor.eshop.service;

import com.vapor.eshop.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-01
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    public Result<?> listCategoryInTree();

}
