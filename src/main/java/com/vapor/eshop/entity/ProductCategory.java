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
@TableName("ProductCategory")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ID", type = IdType.AUTO)
      private Integer id;

    @TableField("Name")
    private String name;

    @TableField("Parent_ID")
    private Integer parentId;

    @TableField("Level")
    private Integer level;

    
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
    
    public Integer getParentId() {
        return parentId;
    }

      public void setParentId(Integer parentId) {
          this.parentId = parentId;
      }
    
    public Integer getLevel() {
        return level;
    }

      public void setLevel(Integer level) {
          this.level = level;
      }

    @Override
    public String toString() {
        return "ProductCategory{" +
              "id=" + id +
                  ", name=" + name +
                  ", parentId=" + parentId +
                  ", level=" + level +
              "}";
    }
}
