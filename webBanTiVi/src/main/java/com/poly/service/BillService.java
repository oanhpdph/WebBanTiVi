package com.poly.service;

import com.poly.dto.SearchBillDto;
import com.poly.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BillService {

    Page<Bill> loadData(SearchBillDto searchBillDto, Pageable pageable);

    Bill add(Bill bill);

    Bill update(Bill bill, Integer id);

    Boolean delete(Integer id);

    Bill getOneById(Integer id);

    Bill getOneByIdCustomer(Integer idCustomer);

    List<Bill> findAllBillByUser(Integer idInteger);
}
