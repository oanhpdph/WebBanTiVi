package com.poly.repository;

import com.poly.entity.VoucherCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer, Integer> {


}
