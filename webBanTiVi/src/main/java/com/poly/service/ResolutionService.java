package com.poly.service;

import com.poly.entity.Resolution;

import java.util.List;

public interface ResolutionService {

    Resolution add(Resolution resolution);

    void delete(Integer id);

    List<Resolution> getAll();
}
