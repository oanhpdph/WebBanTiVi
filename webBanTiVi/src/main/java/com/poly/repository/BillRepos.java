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

    @Query(value = "select b from Bill b where b.code = ?1 or b.customer.name like %?1% ")
    Page<Bill> searchByKey(String key, Pageable pageable);

    @Query(value = "select b from Bill b where b.createDate between ?1 and ?2")
    Page<Bill> searchByDate(Date dateStart, Date dateEnd, Pageable pageable);


    @Query(value = "select b from Bill b where (b.code = ?1 or b.customer.name = %?1%) and b.createDate between ?2 and ?3")
    Page<Bill> searchByKeyandDate(String key, Date dateStart, Date dateEnd, Pageable pageable);

}
