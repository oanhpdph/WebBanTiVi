package com.poly.service.Impl;


import com.poly.entity.Feature;
import com.poly.repository.FeatureRepository;
import com.poly.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    FeatureRepository featureRepository;

    @Override
    public Feature save(Feature feature) {
        return this.featureRepository.save(feature);
    }

    @Override
    public void deleteById(Integer id) {
        this.featureRepository.deleteById(id);
    }

    @Override
    public List<Feature> findAll() {
        return this.featureRepository.findAll();
    }

    @Override
    public Optional<Feature> findById(Integer id) {
        return this.featureRepository.findById(id);
    }
}