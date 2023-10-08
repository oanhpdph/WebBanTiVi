package com.poly.service.Impl;

import com.poly.entity.Voucher;
import com.poly.repository.VoucherRepository;
import com.poly.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public Voucher save(Voucher voucher) {
        return this.voucherRepository.save(voucher);
    }

    @Override
    public void delete(Integer id) {
        this.voucherRepository.deleteById(id);
    }

    @Override
    public List<Voucher> findAll() {
        return this.voucherRepository.findAll();
    }

    @Override
    public Optional<Voucher> findById(Integer id) {
        return this.voucherRepository.findById(id);
    }
}
