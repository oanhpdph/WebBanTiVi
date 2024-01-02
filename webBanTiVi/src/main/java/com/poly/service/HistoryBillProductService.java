package com.poly.service;

import com.poly.dto.CountBillProductReturnDto;
import com.poly.dto.HistoryBillReturnDto;
import com.poly.entity.Bill;
import com.poly.entity.HistoryBillProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistoryBillProductService {

    HistoryBillProduct save(HistoryBillProduct historyBillProduct);

    HistoryBillProduct findByBillProductAndReturnTimes(Integer idBillProduct,Integer idBill);

    List<HistoryBillProduct> findAll();

   List<HistoryBillProduct> findHistoryBillProductReturn(Integer status, Integer id);

   List<CountBillProductReturnDto> findCountBillProductReturnDtos(String id);

   List<HistoryBillReturnDto> findAllHistoryBillReturnByIdBill(Integer id);

   Bill  listBillWhenReturned(String id);

   List<HistoryBillProduct> findAllByStatus(Integer status);
}
