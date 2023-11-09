package com.poly.repository;

import com.poly.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetail, Integer> {
    @Query(value = "select p from ProductDetail p where p.sameProduct=?1")
    List<ProductDetail> findBySameProduct(String sameProduct);
}
