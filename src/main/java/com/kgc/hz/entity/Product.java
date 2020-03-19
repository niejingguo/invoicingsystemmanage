package com.kgc.hz.entity;

import java.io.Serializable;

/**
 * @ClassName Product
 * @Description: TODO
 * @Author NieJingGuo
 * @Date 2020/3/13 15:10
 * @Version V1.0
 **/
public class Product implements Serializable {
    private static final long serialVersionUID =1L;
    private Integer id;
    private String productName;
    private Integer quantity;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
