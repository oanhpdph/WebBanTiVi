package com.poly.service.Impl;


import com.poly.entity.Size;
import com.poly.repository.SizeRepository;
import com.poly.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public Size add(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void delete(Integer id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }
}
