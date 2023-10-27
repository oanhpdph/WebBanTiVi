package com.poly.service;

import com.poly.entity.Color;

import java.util.List;
import java.util.Optional;

public interface ColorService {
    Color add(Color color);

    void delete(Integer id);

    List<Color> findAll();

    Optional<Color> findById(Integer id);
}
