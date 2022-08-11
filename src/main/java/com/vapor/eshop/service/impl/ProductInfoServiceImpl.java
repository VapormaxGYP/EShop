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

        Result<List<ProductInfoListVO>> result = new Result<>();

        if(productInfoForm == null)
            throw new EshopException(ResponseEnum.GET_PRODUCT_INFO_FORM_EMPTY);
        Integer pageNum = productInfoForm.getPageNum();
        Integer pageSize = productInfoForm.getPageSize();

        String keyword = productInfoForm.getKeyword();
        Integer categoryId = productInfoForm.getCategoryId();

        if(pageNum == null || pageSize == null)
            throw new EshopException(ResponseEnum.PAGE_INFO_EMPTY);

        Page<ProductInfo> page = new Page<>(pageNum, pageSize);

        if(keyword == null && categoryId == null){
            List<ProductInfo> productInfoAll = productInfoMapper.selectPage(page, null).getRecords();
            List<ProductInfoListVO> productInfoListVOListAll = productInfoAll.stream()
                    .map(ProductInfoListVO::new).collect(Collectors.toList());

            result.setCode(0);
            result.setMsg("Success Get Product info");
            result.setData(productInfoListVOListAll);

            return result;
        }

        QueryWrapper<ProductInfo> queryWrapperCondition = new QueryWrapper<>();
        //queryWrapperCondition.like("product_name", keyword);
        queryWrapperCondition.like("product_name", keyword)
                .and(wrapper -> wrapper.eq("category_0_ID", categoryId)
                        .or()
                        .eq("category_1_ID", categoryId)
                        .or()
                        .eq("category_2_ID", categoryId));


        List<ProductInfo> productList = productInfoMapper.selectPage(page, queryWrapperCondition).getRecords();
        List<ProductInfoListVO> productInfoListVOList = productList.stream().map(ProductInfoListVO::new).collect(Collectors.toList());

        if(productInfoListVOList.isEmpty())
        {
            result.setCode(500);
            result.setMsg("No Such Product");
            result.setData(productInfoListVOList);
        }
        else
        {
            result.setCode(0);
            result.setMsg("Success get Product List");
            result.setData(productInfoListVOList);
        }
        return result;
    }
}
