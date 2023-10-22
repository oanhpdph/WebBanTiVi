package com.poly.service;

import com.poly.entity.Manufacture;

import java.util.List;

public interface ManufactureService {
    Manufacture add(Manufacture manufacture);

    void delete(Integer id);

    List<Manufacture> getAll();
}
