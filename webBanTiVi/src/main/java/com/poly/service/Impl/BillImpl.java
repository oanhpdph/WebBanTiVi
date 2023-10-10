package com.poly.service.Impl;

import com.poly.dto.BillDto;
import com.poly.entity.Bill;
import com.poly.repository.BillRepos;
import com.poly.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BillImpl implements BillService {
    @Autowired
    private BillRepos billRepos;

    private BillDto billDto = new BillDto();

    @Override
    public Page<Bill> getPagination(Integer pageRequest, Integer sizeRequest) {
        Pageable pageable = PageRequest.of(pageRequest, sizeRequest);
        Page<Bill> pagination = billRepos.findAll(pageable);
        return pagination;
    }

    @Override
    public List<BillDto> getALlDto(Integer pageRequest, Integer sizeRequest) {
        Pageable pageable = PageRequest.of(pageRequest - 1, sizeRequest);
        List<BillDto> listDto = new ArrayList<>();
        for (Map<String, Object> map : billRepos.selectListBill(pageable)) {
            listDto.add(billDto.getDto(map));
        }
        return listDto;
    }

    @Override
    public List<BillDto> search(String data, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<BillDto> listDto = new ArrayList<>();
        for (Map<String, Object> map : billRepos.search(data,pageable)) {
            listDto.add(billDto.getDto(map));
        }
        return listDto;
    }

    @Override
    public Integer getPage(Integer sizeList, Integer pageSize) {
        float page= (float) sizeList / pageSize;
        if(page>(int) page){
            return (int)page+1;
        }
        return (int) page;
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
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
