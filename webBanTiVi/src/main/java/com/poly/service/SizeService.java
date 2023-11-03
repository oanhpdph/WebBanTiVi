package com.poly.service;

import com.poly.entity.Size;

import java.util.List;
import java.util.Optional;

public interface SizeService {
    Size add(Size size);

    void delete(Integer id);

    List<Size> getAll();

    Optional<Size> findById(Integer id);
}
