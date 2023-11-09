//package com.poly.repository;
//
//import com.poly.dto.SearchStaffDto;
//import com.poly.entity.Staff;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface StaffRepository extends JpaRepository<Staff,Integer> {
//
//    Optional<Staff> findByUsername(String code);
//
//    @Query("Select b from Staff b where  b.username like ?1 or b.phone like ?1")
//    Page<Staff> searchByNameOrPhone(String keyword, Pageable pageable);
//
//}
