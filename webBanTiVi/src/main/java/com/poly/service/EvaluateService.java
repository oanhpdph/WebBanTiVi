package com.poly.service;

import com.poly.entity.Evaluate;
import com.poly.dto.EvaluateRes;

import java.util.List;

public interface EvaluateService {

    public List<Evaluate> getAll();

    public void add(EvaluateRes evaluate);

    public void delete(Integer id);

    public Evaluate edit(Integer id);

    Evaluate findById(Integer id);
}
