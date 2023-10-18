package com.poly.service.Impl;


import com.poly.entity.Coupon;
import com.poly.repository.CouponRepository;
import com.poly.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponRepository couponRepository;

    @Override
    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }
    @Override
    public void delete(Integer id) {
        couponRepository.delete(couponRepository.findById(id).get());
    }

    @Override
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }

    @Override
    public Optional<Coupon> findById(Integer id) {
        return couponRepository.findById(id);
    }
}

