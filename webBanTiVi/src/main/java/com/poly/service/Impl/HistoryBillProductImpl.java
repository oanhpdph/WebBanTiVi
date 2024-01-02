package com.poly.service.Impl;

import com.poly.dto.CountBillProductReturnDto;
import com.poly.dto.HistoryBillReturnDto;
import com.poly.entity.Bill;
import com.poly.entity.BillProduct;
import com.poly.entity.HistoryBillProduct;
import com.poly.repository.BillRepos;
import com.poly.repository.HistoryBillProductRepository;
import com.poly.service.BillProductService;
import com.poly.service.HistoryBillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryBillProductImpl implements HistoryBillProductService {

    @Autowired
    BillProductService billProductService;

    @Autowired
    HistoryBillProductRepository historyBillProductRepository;

    @Autowired
    BillRepos billRepos;

    @Override
    public HistoryBillProduct save(HistoryBillProduct historyBillProduct) {
        return this.historyBillProductRepository.save(historyBillProduct);
    }

    @Override
    public HistoryBillProduct findByBillProductAndReturnTimes(Integer idBillProduct, Integer idBill) {
        HistoryBillProduct hBillProduct = new HistoryBillProduct();
        Optional<Integer> returnCount = this.historyBillProductRepository.findReturnCountBillById(idBill);
        List<HistoryBillProduct> historyBillProducts = this.historyBillProductRepository.findAll();
        if(returnCount.isPresent()) {
            for (HistoryBillProduct historyBillProduct : historyBillProducts) {
                if (historyBillProduct.getBillProduct().getId() == idBillProduct &&
                        historyBillProduct.getReturnTimes() == returnCount.get()) {
                    return historyBillProduct;
                }
            }
        }
        return hBillProduct;
    }

    @Override
    public List<HistoryBillProduct> findAll() {
        return this.historyBillProductRepository.findAll();
    }

    @Override
    public List<HistoryBillProduct> findHistoryBillProductReturn(Integer status, Integer id) {
        return this.historyBillProductRepository.findHistoryBillProductReturn(status, id);
    }

    @Override
    public List<CountBillProductReturnDto> findCountBillProductReturnDtos(String id) {
        return null;
    }

    @Override
    public Bill listBillWhenReturned(String id) {
        List<CountBillProductReturnDto> billProductDTOList = new ArrayList<>();
        List<Object[]> resultList = this.historyBillProductRepository.getReturnedDataForBill(Long.parseLong(id));
        for (Object[] result : resultList) {
            CountBillProductReturnDto count = new CountBillProductReturnDto();
            count.setIdBillProduct((Integer) result[0]);
            count.setTotalRequestReturn(Integer.parseInt(String.valueOf((long) result[1])));
            billProductDTOList.add(count);
        }
        Bill bill = this.billRepos.findById(Integer.parseInt(id)).get();
        for (CountBillProductReturnDto countBilProductReturnDto : billProductDTOList) {
            for (BillProduct billProduct : bill.getBillProducts()) {
                if (countBilProductReturnDto.getIdBillProduct() == billProduct.getId()) {
                    billProduct.setQuantity(billProduct.getQuantity() - countBilProductReturnDto.getTotalRequestReturn());
                }

            }
        }
        return bill;
    }

    @Override
    public List<HistoryBillProduct> findAllByStatus(Integer status) {
        return this.historyBillProductRepository.findHistoryBillProductByStatus(status);
    }

    @Override
    public List<HistoryBillReturnDto> findAllHistoryBillReturnByIdBill(Integer id) {
        List<HistoryBillReturnDto> historyBillReturnDtos = new ArrayList<>();
        Optional<Integer> returnMax = this.historyBillProductRepository.findReturnCountBillById(id);
        if( returnMax.isPresent() && returnMax.get() != 0 ) {
            for (int i = 0; i < returnMax.get(); i++) {
                List<HistoryBillProduct> historyBillProducts = this.historyBillProductRepository.findAllHistoryBillReturnByIdBill(id, i + 1);
                HistoryBillReturnDto historyBillReturnDto = new HistoryBillReturnDto();
                historyBillReturnDto.setReturnTimes(i + 1);
                historyBillReturnDto.setHistoryBillProductList(historyBillProducts);
                historyBillReturnDtos.add(historyBillReturnDto);
            }
        }
        return historyBillReturnDtos;
    }

}

