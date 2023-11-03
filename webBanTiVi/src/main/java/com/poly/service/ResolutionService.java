package com.poly.service;

import com.poly.entity.Resolution;

import java.util.List;
import java.util.Optional;

public interface ResolutionService {

    Resolution add(Resolution resolution);

    void delete(Integer id);

    List<Resolution> getAll();

    Optional<Resolution> findById(Integer id);
}
