package com.poly.service.Impl;

import com.poly.entity.Resolution;
//import com.poly.repository.DashBoardRepository;
import com.poly.repository.ResolutionRepository;
import com.poly.service.ResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
