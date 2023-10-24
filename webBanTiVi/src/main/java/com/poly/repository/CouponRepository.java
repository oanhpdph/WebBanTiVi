package com.poly.repository;

import com.poly.dto.CouponRes;
import com.poly.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    @Query(value = "select c.id, c.value, c.image, c.active,cp.dateStart,cp.dateEnd" +
            "from Coupon c join CouponProduct cp on c.id = cp.coupon", nativeQuery = true)
    Page<CouponRes> getCouponRes();
}
