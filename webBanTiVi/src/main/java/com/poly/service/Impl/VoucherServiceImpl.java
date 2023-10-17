package com.poly.service.Impl;

import com.poly.entity.Bill;
import com.poly.entity.Voucher;
import com.poly.repository.VoucherRepository;
import com.poly.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public Page<Voucher> getPagination(Integer pageRequest, Integer sizeRequest) {
        Pageable pageable = PageRequest.of(pageRequest, sizeRequest);
        Page<Voucher> pagination = voucherRepository.findAll(pageable);
        return pagination;
    }

    @Override
    public Page<Voucher> search(String data, String date, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        System.out.println(date);
        Page<Voucher> pagination = null;
        if ("".equals(date)) {
            pagination = this.voucherRepository.searchByKey(data, pageable);
            return pagination;
        }
        String date1 = date.substring(0, date.indexOf("-") - 1).replace("/", "-");
        String date2 = date.substring(date.indexOf("-") + 1, date.length()).replace("/", "-");
        System.out.println(date1 + date2);
        Date dateStart = Date.valueOf(date1.trim());
        Date dateEnd = Date.valueOf(date2.trim());
        if ("".equals(data)) {
            pagination = this.voucherRepository.searchByStartOrExpirationDate(dateStart, dateEnd, pageable);
            return pagination;
        }
        pagination = this.voucherRepository.searchKeyAndStartDateOrExDate(data, dateStart, dateEnd, pageable);
        return pagination;
    }

    @Override
    public Voucher save(Voucher voucher) {
        return this.voucherRepository.save(voucher);
    }

    @Override
    public void delete(Integer id) {
        this.voucherRepository.deleteById(id);
    }

    @Override
    public Page<Voucher> findAll(Pageable pageable) {
        return (Page<Voucher>) this.voucherRepository.findAll(pageable).toList();
    }

    @Override
    public Optional<Voucher> findById(Integer id) {
        return this.voucherRepository.findById(id);
    }
}
