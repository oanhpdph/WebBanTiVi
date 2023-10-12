package com.poly.service;

import com.poly.entity.Bill;
import org.springframework.data.domain.Page;

import java.text.ParseException;

public interface BillService {

    Page<Bill> getPagination(Integer page, Integer size);

    Page<Bill> search(String data,String date, Integer page, Integer size) ;

    Integer getPage(Integer sizeList, Integer pageSize);

    Bill add(Bill bill);

    Bill update(Bill bill, Integer id);

    Boolean delete(Integer id);

    Bill getOneById(Integer id);

    Bill getOneByIdCustomer(Integer idCustomer);
}
