package com.poly.entity.idClass;

import com.poly.entity.Customer;
import com.poly.entity.Voucher;

import java.io.Serializable;

public class VoucherCustomerId implements Serializable {
    private Customer customer;
    private Voucher voucher;
}
