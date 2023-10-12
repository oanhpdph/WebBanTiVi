package com.poly.service;

import com.poly.dto.EvaluateRes;
import com.poly.entity.Evaluate;
import com.poly.repository.EvaluateRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EvaluateService {

    public List<Evaluate> getAll();

    public void add(EvaluateRes evaluate);

    public void delete(Integer id);

    public Evaluate edit(Integer id);
}
