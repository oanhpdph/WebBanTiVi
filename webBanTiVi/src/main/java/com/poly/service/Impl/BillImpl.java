package com.poly.service.Impl;

import com.poly.entity.Bill;
import com.poly.repository.BillRepos;
import com.poly.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Optional;

@Service
public class BillImpl implements BillService {
    @Autowired
    private BillRepos billRepos;


    @Override
    public Page<Bill> getPagination(Integer pageRequest, Integer sizeRequest) {
        Pageable pageable = PageRequest.of(pageRequest, sizeRequest);
        Page<Bill> pagination = billRepos.findAll(pageable);
        return pagination;
    }


    @Override
    public Page<Bill> search(String data, String date, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        System.out.println(date);
        Page<Bill> pagination = null;
        if ("".equals(date)) {
            pagination = billRepos.searchByKey(data, pageable);
            return pagination;
        }
        String date1 = date.substring(0, date.indexOf("-") - 1).replace("/", "-");
        String date2 = date.substring(date.indexOf("-") + 1, date.length()).replace("/", "-");
        System.out.println(date1 + date2);
        Date dateStart = Date.valueOf(date1.trim());
        Date dateEnd = Date.valueOf(date2.trim());
        if ("".equals(data)) {
            pagination = billRepos.searchByDate(dateStart, dateEnd, pageable);
            return pagination;
        }
        pagination = billRepos.searchByKeyandDate(data, dateStart, dateEnd, pageable);
        return pagination;
    }

    @Override
    public Integer getPage(Integer sizeList, Integer pageSize) {
        float page = (float) sizeList / pageSize;
        if (page > (int) page) {
            return (int) page + 1;
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
