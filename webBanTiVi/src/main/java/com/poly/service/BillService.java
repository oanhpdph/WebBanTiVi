package com.poly.service;

import com.poly.entity.Bill;

import java.util.List;

public interface BillService {

    List<Bill> getALl();

    Bill add(Bill bill);

    Bill update(Bill bill, Integer id);

    Boolean delete(Integer id);

    Bill getOneById(Integer id);

    Bill getOneByIdCustomer(Integer idCustomer);
}
