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
@TableName("ProductCategory")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "kind_id", type = IdType.AUTO)
      private Integer kindId;

    private String kindName;

    @TableField("parent_ID")
    private Integer parentId;

    private Integer kindLevel;

    
    public Integer getKindId() {
        return kindId;
    }

      public void setKindId(Integer kindId) {
          this.kindId = kindId;
      }
    
    public String getKindName() {
        return kindName;
    }

      public void setKindName(String kindName) {
          this.kindName = kindName;
      }
    
    public Integer getParentId() {
        return parentId;
    }

      public void setParentId(Integer parentId) {
          this.parentId = parentId;
      }
    
    public Integer getKindLevel() {
        return kindLevel;
    }

      public void setKindLevel(Integer kindLevel) {
          this.kindLevel = kindLevel;
      }

    @Override
    public String toString() {
        return "ProductCategory{" +
              "kindId=" + kindId +
                  ", kindName=" + kindName +
                  ", parentId=" + parentId +
                  ", kindLevel=" + kindLevel +
              "}";
    }
}
