package com.poly.repository;

import com.poly.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface BillRepos extends JpaRepository<Bill, Integer> {
    @Query(value = "select * from Bill b where b.id_customer=?1", nativeQuery = true)
    Optional<Bill> getBillByCustomer(Integer idCustomer);


}
