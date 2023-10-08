package com.poly.service;

import com.poly.entity.Evaluate;
import com.poly.repository.EvaluateRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EvaluateService {

    public List<Evaluate> getAll();

    public void add(Evaluate evaluate);

    public void delete(Integer id);

    public Evaluate edit(Integer id);
}
