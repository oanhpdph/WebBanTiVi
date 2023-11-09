package com.poly.repository;

import com.poly.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE (p.brand.id=?1 or ?1 is null ) and (p.color.id=?2 or ?2 is null) and (p.origin.id=?3 or ?3 is null) and " +
            "(p.resolution.id=?4 or ?4 is null) and (p.size.id=?5  or ?5 is null) and (p.type=?6  or ?5 is null) and p.price_export between ?7 and ?8")
    List<Product>findAllByAll(Integer idBrand, Integer idColor, Integer idOrigin, Integer idReso, Integer idSize,
                              Integer idType, int min,int max);
    @Query("SELECT p FROM Product p WHERE" +
            " (p.name like ?1 and p.brand.nameBrand like ?1 and p.type.nameType like ?1) " +
            "and p.price_export between ?2 and ?3")
    List<Product>findAllByKeyWordAndPrice(String keyword, int min, int max);
}
