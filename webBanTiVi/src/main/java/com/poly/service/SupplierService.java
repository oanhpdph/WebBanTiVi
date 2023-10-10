package com.poly.service;

import com.poly.entity.Product;
import com.poly.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    Supplier save(Supplier supplier);

    void delete(Integer id);

    List<Supplier> findAll();

    Optional<Supplier> findById(Integer id);
}
