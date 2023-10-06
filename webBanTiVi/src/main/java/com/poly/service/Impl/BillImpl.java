package com.poly.service.Impl;

import com.poly.entity.Bill;
import com.poly.repository.BillRepos;
import com.poly.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillImpl implements BillService {
    @Autowired
    private BillRepos billRepos;

    @Override
    public List<Bill> getALl() {
        return billRepos.findAll();
    }

    @Override
    public Bill add(Bill bill) {
        return billRepos.save(bill);
    }

    @Override
    public Bill update(Bill bill, Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    @Override
    public Bill getOneById(Integer id) {
        return null;
    }

    @Override
    public Bill getOneByIdCustomer(Integer idCustomer) {
        return null;
    }
}
