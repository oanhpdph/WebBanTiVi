package com.poly.service.Impl;

import com.poly.entity.Brand;
import com.poly.repository.BrandRepo;
import com.poly.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandImpl implements BrandService {

    @Autowired
    private BrandRepo brandRepo;

    @Override
    public List<Brand> findAll() {
        return brandRepo.findAll();
    }

    @Override
    public Brand findById(Integer id) {
        Optional<Brand> optional = brandRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
