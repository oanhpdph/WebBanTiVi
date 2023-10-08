package com.poly.service.Impl;

import com.poly.entity.Color;

import com.poly.repository.ColorRepository;

import com.poly.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorRepository colorRepository;

    @Override
    public Color add(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void delete(Integer id) {
        colorRepository.deleteById(id);
    }

    @Override
    public List<Color> getAll() {
        return colorRepository.findAll();
    }


//    @Override
//    public Optional<Staff> findById(Integer id) {
//        return staffRepository.findById(id);
//    }
}
