package com.vapor.eshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vapor.eshop.entity.ProductCategory;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.mapper.ProductCategoryMapper;
import com.vapor.eshop.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vapor.eshop.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Override
    public Result<?> listCategoryInTree() {
        Result<List<ProductCategoryVO>> result = new Result<>();

        //get all product category
        List<ProductCategory> list = this.productCategoryMapper.selectList(null);

        List<ProductCategoryVO> voList = list.stream().map(ProductCategoryVO::new).collect(Collectors.toList());


        GenerateMenu(voList);
        result.setCode(0);
        result.setMsg("Success Generate Category");
        result.setData(voList);

        return result;
    }

    public void GenerateMenu(List<ProductCategoryVO> voList){
        List<ProductCategoryVO> levelOneList = voList.stream()
                .filter(productCategoryVO -> productCategoryVO.getKindLevel() == 0)
                .collect(Collectors.toList());

        Iterator<ProductCategoryVO> iterator = levelOneList.iterator();
        while (iterator.hasNext()) {
            ProductCategoryVO next = iterator.next();
            recursion(voList, next);
        }
    }

    public void recursion(List<ProductCategoryVO> voList, ProductCategoryVO productCategoryVO){
        List<ProductCategoryVO> children = getChildren(voList, productCategoryVO);
        productCategoryVO.setChildren(children);

        if(children.size() > 0){
            Iterator<ProductCategoryVO> iterator = children.iterator();
            while (iterator.hasNext()) {
                ProductCategoryVO next = iterator.next();
                recursion(voList, next);
            }

        }

    }

    public List<ProductCategoryVO> getChildren(List<ProductCategoryVO> voList, ProductCategoryVO productCategoryVO){
        List<ProductCategoryVO> children = new ArrayList<>();

        Iterator<ProductCategoryVO> iterator = voList.iterator();
        while (iterator.hasNext()) {
            ProductCategoryVO tmp = iterator.next();
            if(tmp.getParentId() == productCategoryVO.getKindId())
                children.add(tmp);
        }

        return children;
    }
}
