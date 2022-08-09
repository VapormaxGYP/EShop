package com.vapor.eshop.controller;


import com.vapor.eshop.entity.Result;
import com.vapor.eshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/category")
    public Result<?> getProductCategory(){
        return productCategoryService.listCategoryInTree();
    }

}

