package com.poly.service;



import com.poly.entity.Feature;

import java.util.List;
import java.util.Optional;

public interface FeatureService {
    Feature save(Feature feature);

    void delete(Integer id);

    List<Feature> findAll();

    Optional<Feature> findById(Integer id);
}
