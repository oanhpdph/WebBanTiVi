package com.poly.service;

import com.poly.entity.Bill;
import com.poly.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VoucherService {

    Voucher save(Voucher voucher);

    void delete(Integer id);

    Page<Voucher> findAll(Pageable pageable);

    Optional<Voucher> findById(Integer id);

    List<Voucher> findAllList();

    // chia page


    Page<Voucher> getPagination(Integer page, Integer size);

    Page<Voucher> search(String data,String date, Integer page, Integer size) ;
}
