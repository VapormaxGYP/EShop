package com.vapor.eshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vapor.eshop.entity.ProductInfo;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.GetProductInfoForm;
import com.vapor.eshop.mapper.ProductInfoMapper;
import com.vapor.eshop.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vapor.eshop.vo.ProductInfoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public Result<?> getProductList(GetProductInfoForm productInfoForm) {
        if(productInfoForm == null)
            throw new EshopException(ResponseEnum.GET_PRODUCT_INFO_FORM_EMPTY);
        Integer pageNum = productInfoForm.getPageNum();
        Integer pageSize = productInfoForm.getPageSize();

        String keyword = productInfoForm.getKeyword();
        Integer categoryId = productInfoForm.getCategoryId();

        if(pageNum == null || pageSize == null)
            throw new EshopException(ResponseEnum.PAGE_INFO_EMPTY);

        Page<ProductInfo> page = new Page<>(pageNum, pageSize);

        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("product_name", keyword)
                .or()
                .eq("category_0_ID", categoryId)
                .or()
                .eq("category_1_ID", categoryId)
                .or()
                .eq("category_2_ID", categoryId);

        List<ProductInfo> productList = productInfoMapper.selectPage(page, queryWrapper).getRecords();
        List<ProductInfoListVO> productInfoListVOList = productList.stream().map(ProductInfoListVO::new).collect(Collectors.toList());

        Result<List<ProductInfoListVO>> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Get Product List");
        result.setData(productInfoListVOList);
        return result;
    }
}
