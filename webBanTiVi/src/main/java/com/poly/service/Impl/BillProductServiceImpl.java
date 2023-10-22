package com.poly.service.Impl;

import com.poly.entity.BillProduct;
import com.poly.entity.idClass.BillProductId;
import com.poly.repository.BillProductRepos;
import com.poly.service.BillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillProductServiceImpl implements BillProductService {
    @Autowired
    private BillProductRepos repos;

    @Override
    public List<BillProduct> getAll() {
        return repos.findAll();
    }

    @Override
    public void save(BillProduct bp) {
        repos.save(bp);
    }

    @Override
    public void delete(BillProductId id) {
        repos.deleteById(id);
    }

    @Override
    public BillProduct edit(BillProductId id) {
        return repos.findById(id).get();
    }
}
