package com.poly.service;

import com.poly.entity.Staff;
import com.poly.entity.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VoucherService {

    Voucher save(Voucher voucher);

    void delete(Integer id);

    List<Voucher> findAll();

    Optional<Voucher> findById(Integer id);

}
