package com.poly.repository;

import com.poly.entity.VoucherCustomer;
import com.poly.entity.idClass.VoucherCustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer, VoucherCustomerId> {
    @Query("select o from VoucherCustomer o where o.customer.id=?1 and o.voucher.id=?2")
    VoucherCustomer findByCustomerAndVoucher(Integer idcus, Integer idvou);
    @Query("select o from VoucherCustomer o where o.voucher.id=?1")
    List<VoucherCustomer> findAllByVoucher(Integer id);
}
