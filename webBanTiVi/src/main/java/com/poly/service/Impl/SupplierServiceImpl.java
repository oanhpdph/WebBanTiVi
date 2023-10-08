package com.poly.service.Impl;

import com.poly.entity.Supplier;
import com.poly.repository.SupplierRepository;
import com.poly.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void delete(Integer id) {
    supplierRepository.delete(supplierRepository.findById(id).get());
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findById(Integer id) {
        return supplierRepository.findById(id);
    }
}
