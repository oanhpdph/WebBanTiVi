package com.poly.service;

import com.poly.dto.VoucherCustomerRes;
import com.poly.entity.VoucherCustomer;
import com.poly.entity.idClass.VoucherCustomerId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VoucherCustomerService {

    VoucherCustomer save(VoucherCustomerRes voucher);

    void delete(VoucherCustomerId id);

    List<VoucherCustomer> findAll();

    Optional<VoucherCustomer> findById(VoucherCustomerId id);
}
