package com.poly.service;

import com.poly.dto.BillProRes;
import com.poly.dto.SearchBillDto;
import com.poly.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillService {

    Page<Bill> loadData(SearchBillDto searchBillDto, Pageable pageable);

    Bill add(Bill bill);

    public Bill add(BillProRes bill);

    public void addBillPro(Bill bill, BillProRes billProRes);

    Bill update(Bill bill, Integer id);

    Boolean delete(Integer id);

    Bill getOneById(Integer id);


    Bill getOneByIdCustomer(Integer idCustomer);

}
