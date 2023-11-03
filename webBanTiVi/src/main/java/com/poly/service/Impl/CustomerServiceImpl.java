package com.poly.service.Impl;

import com.poly.dto.SearchStaffDto;
import com.poly.entity.Customer;

import com.poly.entity.Staff;
import com.poly.repository.CustomerRepository;
import com.poly.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public void deleteById(Integer id) {
     this.customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public Page<Customer> loadData(SearchStaffDto searchStaffDto, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> customerCriteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = customerCriteriaQuery.from(Customer.class);

        List<Predicate> list = new ArrayList<Predicate>();
        if (!searchStaffDto.getKey().isEmpty()) {
            list.add(criteriaBuilder.or(
                    criteriaBuilder.equal(customerRoot.get("username"), searchStaffDto.getKey()),
                    criteriaBuilder.equal(customerRoot.get("phoneNumber"), searchStaffDto.getKey())));
        }
        customerCriteriaQuery.where(criteriaBuilder.and(list.toArray(new Predicate[list.size()])));
        List<Customer> result = entityManager.createQuery(customerCriteriaQuery).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
        List<Customer> result2 = entityManager.createQuery(customerCriteriaQuery).getResultList();
        Page<Customer> page = new PageImpl<>(result, pageable, result2.size());
        return page;
    }

}
