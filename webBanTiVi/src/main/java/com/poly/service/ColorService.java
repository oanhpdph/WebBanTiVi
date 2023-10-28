package com.poly.service;

import com.poly.entity.Color;

import java.util.List;

public interface ColorService {
    Color add(Color color);

    void delete(Integer id);

    List<Color> getAll();
}
