package com.vapor.eshop.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.form.GetProductInfoForm;
import com.vapor.eshop.service.ProductInfoService;
import com.vapor.eshop.vo.ProductInfoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/productInfo")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;
    @GetMapping("/productList")
    public Result<?> getProductList(@ModelAttribute GetProductInfoForm infoForm){

        Page<ProductInfoListVO> productInfoListVOPage = new Page<>();
        return productInfoService.getProductList(infoForm);
    }


}

