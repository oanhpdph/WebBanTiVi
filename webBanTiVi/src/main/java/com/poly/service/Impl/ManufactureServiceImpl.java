package com.poly.service.Impl;

import com.poly.entity.Manufacture;
import com.poly.repository.ManufactureRepository;
import com.poly.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufactureServiceImpl implements ManufactureService {
    @Autowired
    ManufactureRepository manufactureRepository;

    @Override
    public Manufacture add(Manufacture manufacture) {
        return manufactureRepository.save(manufacture);
    }

    @Override
    public void delete(Integer id) {
        manufactureRepository.deleteById(id);
    }

    @Override
    public List<Manufacture> getAll() {
        return  manufactureRepository.findAll();
    }

    @Override
    public Optional<Manufacture> findById(Integer id) {
        return this.manufactureRepository.findById(id);
    }
}
