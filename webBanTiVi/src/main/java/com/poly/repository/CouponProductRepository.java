package com.poly.repository;

import com.poly.entity.CouponProduct;
import com.poly.entity.idClass.CouponProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponProductRepository extends JpaRepository<CouponProduct, CouponProductId> {
}
