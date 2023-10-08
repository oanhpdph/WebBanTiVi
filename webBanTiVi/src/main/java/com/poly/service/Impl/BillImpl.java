package com.poly.service.Impl;

import com.poly.entity.Bill;
import com.poly.repository.BillRepos;
import com.poly.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Bill> optional = billRepos.findById(id);
        if (optional.isPresent()) {
            Bill billUpdate = optional.get();
            billUpdate.setBillStatus(bill.getBillStatus());
            billUpdate.setPaymentMethod(bill.getPaymentMethod());
            billUpdate.setNote(bill.getNote());
            billUpdate.setCustomer(bill.getCustomer());
            billUpdate.setPaymentDate(bill.getPaymentDate());
            return billRepos.save(billUpdate);
        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<Bill> optional = billRepos.findById(id);
        if (optional.isPresent()) {
            billRepos.delete(optional.get());
            return true;
        }
        return false;
    }

    @Override
    public Bill getOneById(Integer id) {
        Optional<Bill> optional = billRepos.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Bill getOneByIdCustomer(Integer idCustomer) {
        Optional<Bill> optional = billRepos.getBillByCustomer(idCustomer);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
