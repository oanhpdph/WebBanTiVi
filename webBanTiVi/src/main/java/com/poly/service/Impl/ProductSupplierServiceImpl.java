package com.poly.service.Impl;

import com.poly.entity.ProductSupplier;
import com.poly.entity.idClass.ProductSupplierId;
import com.poly.repository.ProductSupplierRepository;
import com.poly.service.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSupplierServiceImpl implements ProductSupplierService {
    @Autowired
    ProductSupplierRepository productsupplierRepository;
    @Override
    public ProductSupplier save(ProductSupplier productSupplier) {
        return productsupplierRepository.save(productSupplier);
    }

    @Override
    public void delete(ProductSupplierId id) {
    productsupplierRepository.delete(productsupplierRepository.findById(id).get());
    }

    @Override
    public List<ProductSupplier> findAll() {
        return productsupplierRepository.findAll();
    }

    @Override
    public Optional<ProductSupplier> findById(ProductSupplierId id) {
        return productsupplierRepository.findById(id);
    }
}
