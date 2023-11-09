//package com.poly.service.Impl;
//
//import com.poly.dto.SearchStaffDto;
//import com.poly.entity.Staff;
//import com.poly.entity.Voucher;
//import com.poly.repository.StaffRepository;
//import com.poly.service.StaffService;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//public class StaffServiceImpl implements StaffService {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Autowired
//    StaffRepository staffRepository;
//
//    @Override
//    public Staff save(Staff staff) {
//        return staffRepository.save(staff);
//    }
//
//    @Override
//    public void delete(Integer id) {
//        staffRepository.deleteById(id);
//    }
//
//    @Override
//    public List<Staff> findAll() {
//        return staffRepository.findAll();
//    }
//
//    @Override
//    public Optional<Staff> findById(Integer id) {
//        return staffRepository.findById(id);
//    }
//
//    @Override
//    public Page<Staff> loadData(SearchStaffDto searchStaffDto, Pageable pageable) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Staff> staffCriteriaQuery = criteriaBuilder.createQuery(Staff.class);
//        Root<Staff> staffRoot = staffCriteriaQuery.from(Staff.class);
//
//        List<Predicate> list = new ArrayList<Predicate>();
//        if (!searchStaffDto.getKey().isEmpty()) {
//            list.add(criteriaBuilder.or(
//                    criteriaBuilder.equal(staffRoot.get("username"), searchStaffDto.getKey()),
//                    criteriaBuilder.equal(staffRoot.get("phone"), searchStaffDto.getKey())));
//        }
//        staffCriteriaQuery.where(criteriaBuilder.and(list.toArray(new Predicate[list.size()])));
//        List<Staff> result = entityManager.createQuery(staffCriteriaQuery).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
//        List<Staff> result2 = entityManager.createQuery(staffCriteriaQuery).getResultList();
//        Page<Staff> page = new PageImpl<>(result, pageable, result2.size());
//        return page;
//    }
//
//
//}
