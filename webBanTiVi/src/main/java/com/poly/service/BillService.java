package com.poly.service;

import com.poly.dto.BillDto;
import com.poly.entity.Bill;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {

    Page<Bill> getPagination(Integer page, Integer size);

    List<BillDto> getALlDto(Integer page, Integer size);

    List<BillDto> search(String data,Integer page, Integer size);

    Integer getPage(Integer sizeList, Integer pageSize);

    Bill add(Bill bill);

    Bill update(Bill bill, Integer id);

    Boolean delete(Integer id);

    Bill getOneById(Integer id);

    Bill getOneByIdCustomer(Integer idCustomer);
}
