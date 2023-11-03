package com.poly.service.Impl;

import com.poly.dto.VoucherCustomerRes;
import com.poly.entity.Customer;
import com.poly.entity.Voucher;
import com.poly.entity.VoucherCustomer;
import com.poly.entity.idClass.VoucherCustomerId;
import com.poly.repository.CustomerRepository;
import com.poly.repository.VoucherCustomerRepository;
import com.poly.repository.VoucherRepository;
import com.poly.service.VoucherCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherCustomerServiceImpl implements VoucherCustomerService {
    @Autowired
    VoucherCustomerRepository voucherCustomerRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VoucherRepository voucherRepository;
    public List<VoucherCustomer> findAllByVoucher(Integer id){
        return  voucherCustomerRepository.findAllByVoucher(id);
    }
    @Override
    public VoucherCustomer save(VoucherCustomerRes voucher) {
        Optional<Customer> optionalCustomer = customerRepository.findById(voucher.getCustomer());
        Optional<Voucher> optionalVoucher = voucherRepository.findById(voucher.getVoucher());
        VoucherCustomer voucherCustomer = new VoucherCustomer();
        voucherCustomer.setCustomer(optionalCustomer.get());
        voucherCustomer.setVoucher(optionalVoucher.get());
        voucherCustomer.setDateEnd(voucher.getDate_end());
        voucherCustomer.setDateStart(voucher.getDate_start());
        voucherCustomer.setActive(voucher.getActive());
        return  this.voucherCustomerRepository.save(voucherCustomer);

    }

    @Override
    public void delete(VoucherCustomerId id) {
        this.voucherCustomerRepository.deleteById(id);
    }

    @Override
    public List<VoucherCustomer> findAll() {
        return this.voucherCustomerRepository.findAll();
    }

    @Override
    public Optional<VoucherCustomer> findById(VoucherCustomerId id) {
        return this.voucherCustomerRepository.findById(id);
    }
}
