package com.poly.repository;

import com.poly.dto.CouponRes;
import com.poly.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    @Query(value = "select p.id, p.value, p.image, p.active, cp.date_Start, cp.date_End,cp.id_product " +
            "from Coupon p join Coupon_Product cp on p.id = cp.id_coupon where cp.date_Start < ?1 " +
            "  AND cp.date_End > ?1 ", nativeQuery = true)
    List<CouponRes> getCouponRes(Date today);
}
