package com.vapor.eshop.service.impl;

import com.vapor.eshop.entity.ProductCategory;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.mapper.ProductCategoryMapper;
import com.vapor.eshop.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vapor.eshop.vo.ProductCategoryTemp;
import com.vapor.eshop.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Override
    public Result<?> listCategoryInTree() {
        Result<List<ProductCategoryVO>> result = new Result<>();

        //get all product category
        List<ProductCategory> list = this.productCategoryMapper.selectList(null);

        List<ProductCategoryTemp> tmpList = list.stream().map(ProductCategoryTemp::new).collect(Collectors.toList());
        List<ProductCategoryVO> resList = tmpList.stream().filter(tmp -> tmp.getLevel() == 0)
                .map(ProductCategoryVO::new)
                .collect(Collectors.toList());

        GenerateMenu(tmpList);
        SetVO(tmpList, resList);

        result.setCode(0);
        result.setMsg("Success Generate Category");
        result.setData(resList);

        return result;
    }

    public void SetVO(List<ProductCategoryTemp> tempList, List<ProductCategoryVO> voList){
        if(tempList.size() == 0)
            return;
        Iterator<ProductCategoryTemp> iteratorTmp = tempList.iterator();
        Iterator<ProductCategoryVO> iteratorVo = voList.iterator();
        while (iteratorTmp.hasNext() && iteratorVo.hasNext()) {
            ProductCategoryTemp nextTmp = iteratorTmp.next();
            ProductCategoryVO nextVo = iteratorVo.next();

            List<ProductCategoryTemp> children = nextTmp.getChildren();
            List<ProductCategoryVO> childrenVO = children.stream().map(ProductCategoryVO::new).collect(Collectors.toList());
            nextVo.setChildren(childrenVO);

            SetVO(children, childrenVO);
        }
    }

    public void GenerateMenu(List<ProductCategoryTemp> tmpList){
        List<ProductCategoryTemp> levelOneList = tmpList.stream()
                .filter(productCategoryTemp -> productCategoryTemp.getLevel() == 0)
                .collect(Collectors.toList());

        Iterator<ProductCategoryTemp> iterator = levelOneList.iterator();
        while (iterator.hasNext()) {
            ProductCategoryTemp nextTmp = iterator.next();
            recursion(tmpList, nextTmp);
        }
    }

    public void recursion(List<ProductCategoryTemp> tmpList, ProductCategoryTemp productCategoryTemp){
        List<ProductCategoryTemp> children = getChildren(tmpList, productCategoryTemp);
        productCategoryTemp.setChildren(children);

        if(children.size() > 0){
            Iterator<ProductCategoryTemp> iterator = children.iterator();
            while (iterator.hasNext()) {
                ProductCategoryTemp tmp = iterator.next();
                recursion(tmpList, tmp);
            }
        }

    }

    public List<ProductCategoryTemp> getChildren(List<ProductCategoryTemp> voList, ProductCategoryTemp productCategoryTemp){
        List<ProductCategoryTemp> children = new ArrayList<>();

        Iterator<ProductCategoryTemp> iterator = voList.iterator();
        while (iterator.hasNext()) {
            ProductCategoryTemp tmp = iterator.next();
            if(Objects.equals(tmp.getParentId(), productCategoryTemp.getKey()))
                children.add(tmp);
        }

        return children;
    }
}
