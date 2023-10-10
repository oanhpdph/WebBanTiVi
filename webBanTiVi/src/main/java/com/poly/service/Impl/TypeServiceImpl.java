package com.poly.service.Impl;

import com.poly.entity.Type;
import com.poly.repository.TypeRepository;
import com.poly.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeRepository typeRepository;
    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void delete(Integer id) {
    typeRepository.delete(typeRepository.findById(id).get());
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Integer id) {
        return typeRepository.findById(id);
    }
}
