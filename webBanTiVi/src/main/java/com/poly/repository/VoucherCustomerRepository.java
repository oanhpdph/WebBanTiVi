package com.poly.repository;

import com.poly.entity.VoucherCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
<<<<<<< HEAD
public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer, VoucherCustomerId> {
    @Query("select o from VoucherCustomer o where o.customer.id=?1 and o.voucher.id=?2")
    VoucherCustomer findByCustomerAndVoucher(Integer idcus, Integer idvou);
    @Query("select o from VoucherCustomer o where o.voucher.id=?1")
    List<VoucherCustomer> findAllByVoucher(Integer id);
=======
public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer, Integer> {


>>>>>>> 72c2e6de8117f04f47122b32ea494893d838d981
}
