package com.poly.service;

import com.poly.entity.Supplier;
import com.poly.entity.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {
    Type save(Type type);

    void delete(Integer id);

    List<Type> findAll();

    Optional<Type> findById(Integer id);
}
