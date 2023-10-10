package com.poly.service;

import com.poly.entity.CouponProduct;
import com.poly.entity.idClass.CouponProductId;

import java.util.List;
import java.util.Optional;

public interface CouponProductService {
    CouponProduct save(CouponProduct couponProduct);

    void delete(CouponProductId id);

    List<CouponProduct> findAll();

    Optional<CouponProduct> findById(CouponProductId id);
}
