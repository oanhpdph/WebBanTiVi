package com.poly.repository;

import com.poly.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageProductRepository extends JpaRepository<ImageProduct,Integer> {
    @Query("SELECT a FROM ImageProduct a " +
            "JOIN Product b ON a.product.id = b.id " +
            "WHERE b.id = ?1")
    List<ImageProduct> findByProductDetailId(Integer id);
}
