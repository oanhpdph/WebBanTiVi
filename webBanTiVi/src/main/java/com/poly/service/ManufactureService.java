package com.poly.service;

import com.poly.entity.Manufacture;

import java.util.List;
import java.util.Optional;

public interface ManufactureService {
    Manufacture add(Manufacture manufacture);

    void delete(Integer id);

    List<Manufacture> getAll();

    Optional<Manufacture> findById(Integer id);
}
