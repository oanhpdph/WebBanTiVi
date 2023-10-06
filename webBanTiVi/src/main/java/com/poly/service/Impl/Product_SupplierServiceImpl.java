package com.poly.service.Impl;

import com.poly.entity.ProductSupplier;
import com.poly.entity.idClass.ProductSupplierId;
import com.poly.repository.Product_SupplierRepository;
import com.poly.service.Product_supplierService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class Product_SupplierServiceImpl implements Product_supplierService {
    @Autowired
    Product_SupplierRepository product_supplierRepository;
    @Override
    public ProductSupplier save(ProductSupplier productSupplier) {
        return product_supplierRepository.save(productSupplier);
    }

    @Override
    public void delete(ProductSupplierId id) {
    product_supplierRepository.delete(product_supplierRepository.findById(id).get());
    }

    @Override
    public List<ProductSupplier> findAll() {
        return product_supplierRepository.findAll();
    }

    @Override
    public Optional<ProductSupplier> findById(ProductSupplierId id) {
        return product_supplierRepository.findById(id);
    }
}
