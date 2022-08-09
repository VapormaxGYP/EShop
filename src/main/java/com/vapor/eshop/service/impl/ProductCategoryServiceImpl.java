package com.vapor.eshop.service.impl;

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
 * @since 2022-08-01
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Override
    public Result<?> listCategoryInTree() {
        Result<List<ProductCategoryVO>> result = new Result<>();
        //search all product category info
        List<ProductCategory> productCategoryList = this.productCategoryMapper.selectList(null);

        //Turn all productCategory list to pcVO
        List<ProductCategoryVO> productCategoryVOList = productCategoryList.stream().map(ProductCategoryVO::new).collect(Collectors.toList());

        //Generate Category menu
        generateMenu(productCategoryVOList);

        result.setCode(200);
        result.setMsg("Success");
        result.setData(productCategoryVOList);

        return result;
    }

    public void generateMenu(List<ProductCategoryVO> productCategoryVOList){
        List<ProductCategoryVO> levelOneList = productCategoryVOList
                .stream()
                .filter(pcVO -> pcVO.getLevel() == 0)
                .collect(Collectors.toList());

        for(ProductCategoryVO levelOneProduct : levelOneList){
            recursion(productCategoryVOList, levelOneProduct);
        }
    }

    public void recursion(List<ProductCategoryVO> list, ProductCategoryVO productCategoryVO){
        List<ProductCategoryVO> children = getChildren(list, productCategoryVO);
        productCategoryVO.setChildren(children);

        if(children.size() > 0){
            Iterator<ProductCategoryVO> iterator = children.iterator();
            while (iterator.hasNext()) {
                ProductCategoryVO next = iterator.next();
                recursion(list, next);
            }
        }
    }

    public List<ProductCategoryVO> getChildren(List<ProductCategoryVO> list, ProductCategoryVO productCategoryVO){
        List<ProductCategoryVO> children = new ArrayList<>();
        Iterator<ProductCategoryVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            ProductCategoryVO next = iterator.next();
            if(next.getParentid().equals(productCategoryVO.getId()))
                children.add(next);
        }
        return children;
    }
}
