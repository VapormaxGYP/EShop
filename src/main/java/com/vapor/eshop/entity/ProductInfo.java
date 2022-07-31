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
 * @since 2022-08-01
 */
@TableName("ProductInfo")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ID", type = IdType.AUTO)
      private Integer id;

    @TableField("Name")
    private String name;

    @TableField("Description")
    private String description;

    @TableField("Price")
    private Integer price;

    @TableField("Stock")
    private Integer stock;

    @TableField("Category_0_ID")
    private Integer category0Id;

    @TableField("Category_1_ID")
    private Integer category1Id;

    @TableField("Category_2_ID")
    private Integer category2Id;

    @TableField("Picture")
    private String picture;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getDescription() {
        return description;
    }

      public void setDescription(String description) {
          this.description = description;
      }
    
    public Integer getPrice() {
        return price;
    }

      public void setPrice(Integer price) {
          this.price = price;
      }
    
    public Integer getStock() {
        return stock;
    }

      public void setStock(Integer stock) {
          this.stock = stock;
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
    
    public String getPicture() {
        return picture;
    }

      public void setPicture(String picture) {
          this.picture = picture;
      }

    @Override
    public String toString() {
        return "ProductInfo{" +
              "id=" + id +
                  ", name=" + name +
                  ", description=" + description +
                  ", price=" + price +
                  ", stock=" + stock +
                  ", category0Id=" + category0Id +
                  ", category1Id=" + category1Id +
                  ", category2Id=" + category2Id +
                  ", picture=" + picture +
              "}";
    }
}
