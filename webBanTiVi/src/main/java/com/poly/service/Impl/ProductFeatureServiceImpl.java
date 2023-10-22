package com.poly.service.Impl;


import com.poly.entity.ProductFeature;

import com.poly.entity.idClass.ProductFeatureId;

import com.poly.repository.ProductFeatureRepository;
import com.poly.service.ProductFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductFeatureServiceImpl implements ProductFeatureService {
    @Autowired
    ProductFeatureRepository featureRepository;
    @Override
    public ProductFeature save(ProductFeature productFeature) {
        return featureRepository.save(productFeature);
    }

    @Override
    public void delete(ProductFeatureId id) {
        featureRepository.delete(featureRepository.findById(id).get());
    }

    @Override
    public List<ProductFeature> findAll() {
        return featureRepository.findAll();
    }

    @Override
    public Optional<ProductFeature> findById(ProductFeatureId id) {
        return featureRepository.findById(id);
    }
}
