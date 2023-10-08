package com.poly.repository;

import com.poly.entity.ProductSupplier;
import com.poly.entity.idClass.ProductSupplierId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_SupplierRepository extends JpaRepository<ProductSupplier, ProductSupplierId> {
}
