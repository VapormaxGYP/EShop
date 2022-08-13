package com.vapor.eshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vapor.eshop.DO.ProductInfoListDO;
import com.vapor.eshop.entity.ProductInfo;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.GetProductInfoForm;
import com.vapor.eshop.mapper.ProductInfoMapper;
import com.vapor.eshop.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vapor.eshop.vo.ProductDetailVO;
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

        Result<ProductInfoListVO> result = new Result<>();

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
            List<ProductInfoListDO> productInfoListDOListAll = productInfoAll.stream()
                    .map(ProductInfoListDO::new).collect(Collectors.toList());
            Long totalPages = page.getPages();
            Long totalItems = page.getTotal();
            ProductInfoListVO productInfoListVO = new ProductInfoListVO(productInfoListDOListAll, totalPages,totalItems);
            result.setCode(0);
            result.setMsg("Success Get Product info");
            result.setData(productInfoListVO);

            return result;
        }

        if(keyword == null){
            QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_0_ID", categoryId)
                    .or()
                    .eq("category_1_ID", categoryId)
                    .or()
                    .eq("category_2_ID", categoryId);



            List<ProductInfo> productInfos = this.productInfoMapper.selectPage(page,queryWrapper).getRecords();
            List<ProductInfoListDO> productInfoListDOList = productInfos.stream()
                    .map(ProductInfoListDO::new)
                    .collect(Collectors.toList());

            Long totalPages = page.getPages();
            Long totalItems = page.getTotal();

            ProductInfoListVO productInfoListVO = new ProductInfoListVO(productInfoListDOList, totalPages, totalItems);

            result.setCode(0);
            result.setMsg("Success Get Product info");
            result.setData(productInfoListVO);
            return result;
        }

        if(categoryId == null){
            QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("product_name", keyword);

            List<ProductInfo> productInfos = this.productInfoMapper.selectPage(page, queryWrapper).getRecords();
            List<ProductInfoListDO> productInfoListDOList = productInfos.stream()
                    .map(ProductInfoListDO::new)
                    .collect(Collectors.toList());
            Long totalPages = page.getPages();
            Long totalItems = page.getTotal();
            ProductInfoListVO productInfoListVO = new ProductInfoListVO(productInfoListDOList, totalPages, totalItems);
            result.setCode(0);
            result.setMsg("Success Get Product info");
            result.setData(productInfoListVO);
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
        List<ProductInfoListDO> productInfoListDOList = productList.stream().map(ProductInfoListDO::new).collect(Collectors.toList());

        Long totalPages = page.getPages();
        Long totalItems = page.getTotal();
        ProductInfoListVO productInfoListVO = new ProductInfoListVO(productInfoListDOList, totalPages, totalItems);
        if(productInfoListVO.getInfoList().isEmpty())
        {
            result.setCode(0);
            result.setMsg("No Such Product");
            result.setData(productInfoListVO);
        }
        else
        {
            result.setCode(0);
            result.setMsg("Success get Product List");
            result.setData(productInfoListVO);
        }
        return result;
    }

    @Override
    public Result<?> getProductInfo(Integer productId) {
        if(productId == null)
            throw new EshopException(ResponseEnum.EMPTY_PRODUCT_ID);

        Result<ProductDetailVO> result = new Result<>();

        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);

        ProductInfo productInfo = this.productInfoMapper.selectOne(queryWrapper);
        ProductDetailVO productDetailVO = new ProductDetailVO(productInfo);

        result.setCode(0);
        result.setMsg("Success Get Product Detail");
        result.setData(productDetailVO);
        return result;
    }
}
