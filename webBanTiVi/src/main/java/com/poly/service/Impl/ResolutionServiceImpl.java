package com.poly.service.Impl;

import com.poly.entity.Resolution;
import com.poly.repository.ResolutionRepository;
import com.poly.service.ResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResolutionServiceImpl implements ResolutionService {
    @Autowired
    ResolutionRepository resolutionRepository;

    @Override
    public Resolution add(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }

    @Override
    public void delete(Integer id) {
        resolutionRepository.deleteById(id);
    }

    @Override
    public List<Resolution> getAll() {
        return resolutionRepository.findAll();
    }

    @Override
    public Optional<Resolution> findById(Integer id) {
        return this.resolutionRepository.findById(id);
    }
}
