package com.poly.repository;

import com.poly.entity.Bill;
import com.poly.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Integer> {
    @Query(value = "select * from Voucher b where b.id like %?1%", nativeQuery = true)
    Optional<Voucher> getVoucherByName(Integer id);

    @Query(value = "select b from Voucher b where b.code = ?1 or b.nameVoucher like %?1% ")
    Page<Voucher> searchByKey(String key, Pageable pageable);

    @Query(value = "select b from Voucher b where b.startDay between ?1 and ?2 or b.expirationDate between ?1 and ?2 ")
    Page<Voucher> searchByStartOrExpirationDate(Date dateStart, Date dateEnd, Pageable pageable);

    @Query(value = "select b from Voucher b where b.startDay>= ?1 and b.expirationDate <= ?2")
    Page<Voucher> searchByStartAndExpirationDate(Date dateStart, Date dateEnd, Pageable pageable);

    @Query(value = "select b from Voucher b where (b.code = ?1 or b.nameVoucher like %?1%) and (b.startDay between ?2 and ?3 or b.expirationDate between ?2 and ?3)")
    Page<Voucher> searchKeyAndStartDateOrExDate(String key, Date dateStart, Date dateEnd, Pageable pageable);

    @Query(value = "select b from Voucher b where (b.code = ?1 or b.nameVoucher like %?1%) and (b.startDay >= ?2 and  b.expirationDate <=?3)")
    Page<Voucher> searchByKeyAndStartDateAndExDate(String key, Date dateStart, Date dateEnd, Pageable pageable);
}
