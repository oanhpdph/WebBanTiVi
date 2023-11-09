package com.poly.service.Impl;

import com.poly.dto.EvaluateRes;
import com.poly.entity.*;
import com.poly.repository.*;
import com.poly.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    EvaluateRepos evaluateRepos;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDetailRepo detailRepo;

    @Override
    public List<Evaluate> getAll() {
        return evaluateRepos.findAll();
    }

    @Override
    public void add(EvaluateRes evaluate) {
        Optional<Customer> optionalCustomer = customerRepository.findById(evaluate.getCustomer());
        Optional<ProductDetail> optionalProduct = detailRepo.findById(evaluate.getProduct());
        Evaluate eval= new Evaluate();
        eval.setId(evaluate.getId());
        eval.setCustomer(optionalCustomer.get());
        eval.setProduct(optionalProduct.get());
        eval.setDateCreate(evaluate.getDateCreate());
        eval.setPoint(evaluate.getPoint());
        eval.setComment(evaluate.getComment());
        this.evaluateRepos.save(eval);
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
