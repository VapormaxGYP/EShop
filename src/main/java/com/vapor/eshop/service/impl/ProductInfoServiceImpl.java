package com.vapor.eshop.service.impl;

import com.vapor.eshop.entity.ProductInfo;
import com.vapor.eshop.mapper.ProductInfoMapper;
import com.vapor.eshop.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

}
