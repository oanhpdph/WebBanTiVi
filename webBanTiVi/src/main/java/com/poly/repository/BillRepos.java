package com.poly.repository;

import com.poly.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BillRepos extends JpaRepository<Bill, Integer> {
    @Query(value = "select * from Bill b where b.id_customer=?1", nativeQuery = true)
    Optional<Bill> getBillByCustomer(Integer idCustomer);

    @Query(value = "SELECT b.id, b.code, c.name, SUM(pr.quantity * pr.price) AS totalPrice, b.create_date,bs.status " +
            "FROM bill b " +
            "INNER JOIN bill_product pr ON b.id = pr.id_bill " +
            "INNER JOIN customer c ON b.id_customer = c.id " +
            "INNER JOIN bill_status bs ON bs.id = b.id_status " +
            "GROUP BY b.code, c.name, b.create_date, bs.status,b.id order by b.id", nativeQuery = true)
    List<Map<String,Object>> selectListBill(Pageable pageable);

    @Query(value = "SELECT b.id, b.code, c.name, SUM(pr.quantity * pr.price) AS totalPrice, b.create_date,bs.status " +
            "FROM bill b " +
            "INNER JOIN bill_product pr ON b.id = pr.id_bill " +
            "INNER JOIN customer c ON b.id_customer = c.id " +
            "INNER JOIN bill_status bs ON bs.id = b.id_status " +
            "where b.code=?1 or c.name=?1 " +
            "GROUP BY b.code, c.name, b.create_date, bs.status,b.id,c.name order by b.id", nativeQuery = true)
    Page<Map<String,Object>> search(String data,Pageable pageable);
}
