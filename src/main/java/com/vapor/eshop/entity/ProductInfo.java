package com.vapor.eshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
@TableName("ProductInfo")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "product_id", type = IdType.AUTO)
      private Integer productId;

    private String productName;

    private String productDesc;

    private Integer productPrice;

    private Integer productStock;

    @TableField("category_0_ID")
    private Integer category0Id;

    @TableField("category_1_ID")
    private Integer category1Id;

    @TableField("category_2_ID")
    private Integer category2Id;

    private String productPicture;

    
    public Integer getProductId() {
        return productId;
    }

      public void setProductId(Integer productId) {
          this.productId = productId;
      }
    
    public String getProductName() {
        return productName;
    }

      public void setProductName(String productName) {
          this.productName = productName;
      }
    
    public String getProductDesc() {
        return productDesc;
    }

      public void setProductDesc(String productDesc) {
          this.productDesc = productDesc;
      }
    
    public Integer getProductPrice() {
        return productPrice;
    }

      public void setProductPrice(Integer productPrice) {
          this.productPrice = productPrice;
      }
    
    public Integer getProductStock() {
        return productStock;
    }

      public void setProductStock(Integer productStock) {
          this.productStock = productStock;
      }
    
    public Integer getCategory0Id() {
        return category0Id;
    }

      public void setCategory0Id(Integer category0Id) {
          this.category0Id = category0Id;
      }
    
    public Integer getCategory1Id() {
        return category1Id;
    }

      public void setCategory1Id(Integer category1Id) {
          this.category1Id = category1Id;
      }
    
    public Integer getCategory2Id() {
        return category2Id;
    }

      public void setCategory2Id(Integer category2Id) {
          this.category2Id = category2Id;
      }
    
    public String getProductPicture() {
        return productPicture;
    }

      public void setProductPicture(String productPicture) {
          this.productPicture = productPicture;
      }

    @Override
    public String toString() {
        return "ProductInfo{" +
              "productId=" + productId +
                  ", productName=" + productName +
                  ", productDesc=" + productDesc +
                  ", productPrice=" + productPrice +
                  ", productStock=" + productStock +
                  ", category0Id=" + category0Id +
                  ", category1Id=" + category1Id +
                  ", category2Id=" + category2Id +
                  ", productPicture=" + productPicture +
              "}";
    }
}
