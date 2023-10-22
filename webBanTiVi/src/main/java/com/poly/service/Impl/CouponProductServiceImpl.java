package com.poly.service.Impl;

import com.poly.entity.CouponProduct;
import com.poly.entity.idClass.CouponProductId;
import com.poly.repository.CouponProductRepository;
import com.poly.service.CouponProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponProductServiceImpl implements CouponProductService {
    @Autowired
    CouponProductRepository productRepository;
    @Override
    public CouponProduct save(CouponProduct couponProduct) {
        return productRepository.save(couponProduct);
    }

    @Override
    public void delete(CouponProductId id) {
    productRepository.delete(productRepository.findById(id).get());
    }

    @Override
    public List<CouponProduct> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<CouponProduct> findById(CouponProductId id) {
        return productRepository.findById(id);
    }
}
