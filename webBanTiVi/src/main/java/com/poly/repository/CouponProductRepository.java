package com.poly.repository;

import com.poly.entity.CouponProduct;
import com.poly.entity.Product;
import com.poly.entity.idClass.CouponProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponProductRepository extends JpaRepository<CouponProduct, CouponProductId> {

    @Query("select product from CouponProduct where coupon.id=?1")
    List<Product> findAllByCouponId(Integer id);

}
