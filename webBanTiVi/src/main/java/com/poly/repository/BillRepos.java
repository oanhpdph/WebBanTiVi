package com.poly.repository;

import com.poly.entity.Bill;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.sql.Date;

import java.util.List;

import java.util.Optional;

@Repository
public interface BillRepos extends JpaRepository<Bill, Integer> {


    @Query(value = "select * from Bill b where b.id_customer=?1", nativeQuery = true)
    Optional<Bill> getBillByCustomer(Integer idCustomer);

    @Query(value = "select  b from Bill b  where b.paymentDate =?1")
    List<Bill> findBillByDate(Date date);


    @Query(value = "select  b from Bill b  where b.customer.id =?1")
    List<Bill> findBillByUser(Integer id);

//    @Query("SELECT new com.poly.dto.BillDto(b.id,b.code,c.quantity, b.billStatus," +
//            " b.totalPrice, b.billProducts,b.paymentMethod," +
//            "d.received,d.receiverPhone,d.deliveryDate," +
//            "d.receivedDate,d.deliveryFee,d.receivingAddress,d.note) " +
//            "FROM Bill b " +
//            "INNER JOIN BillProduct c ON b.id = c.bill.id LEFT JOIN DeliveryNotes d ON b.id=d.idBill.id  WHERE b.customer.id=?1")
//    List<BillDto> findBillDto(Integer id);


}
