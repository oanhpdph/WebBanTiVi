package com.poly.repository;

import com.poly.entity.ProductFeature;
import com.poly.entity.idClass.ProductFeatureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeatureRepository extends JpaRepository<ProductFeature, ProductFeatureId> {
}
