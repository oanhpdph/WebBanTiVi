package com.poly.dto;

import com.poly.entity.Product;

import java.util.Date;
import java.util.List;

public interface CouponRes {
    Integer getIdCoupon();
    String getValue();
    String getImage();
    Boolean getActive();
    Date getDateStart();
    Date getDateEnd();
    List<Product> getListProduct();

}
