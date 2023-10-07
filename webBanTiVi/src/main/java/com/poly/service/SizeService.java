package com.poly.service;

import com.poly.entity.Size;

import java.util.List;

public interface SizeService {
    Size add(Size size);

    void delete(Integer id);

    List<Size> getAll();
}
