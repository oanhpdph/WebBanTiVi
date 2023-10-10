package com.poly.service.Impl;

import com.poly.entity.Evaluate;
import com.poly.repository.EvaluateRepos;
import com.poly.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    EvaluateRepos evaluateRepos;

    @Override
    public List<Evaluate> getAll() {
        return evaluateRepos.findAll();
    }

    @Override
    public void add(Evaluate evaluate) {
        evaluateRepos.save(evaluate);
    }

    @Override
    public void delete(Integer id) {
        evaluateRepos.deleteById(id);
    }

    @Override
    public Evaluate edit(Integer id) {
        return evaluateRepos.findById(id).get();
    }
}
