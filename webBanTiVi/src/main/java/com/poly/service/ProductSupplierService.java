package com.poly.service;

import com.poly.entity.Product;
import com.poly.entity.ProductSupplier;
import com.poly.entity.idClass.ProductSupplierId;

import java.util.List;
import java.util.Optional;

public interface ProductSupplierService {
    ProductSupplier save(ProductSupplier productSupplier);

    void delete(ProductSupplierId id);

    List<ProductSupplier> findAll();

    Optional<ProductSupplier> findById(ProductSupplierId id);
}
