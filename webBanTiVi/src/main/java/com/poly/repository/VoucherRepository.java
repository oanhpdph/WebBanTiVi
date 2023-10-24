package com.poly.repository;

<<<<<<< HEAD
import com.poly.dto.CouponRes;
=======
>>>>>>> e7371f70ad8a0e6c59f0eb32b41810c4de498ad5
import com.poly.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Integer> {
<<<<<<< HEAD

    @Query(value = "select * from Voucher b where b.id like %?1%", nativeQuery = true)
    Optional<Voucher> getVoucherByName(Integer id);
=======
    @Query(value = "select b from Voucher b where b.id = ?1 ")
    Optional<Voucher> getVoucherById(Integer id);
>>>>>>> e7371f70ad8a0e6c59f0eb32b41810c4de498ad5


}
