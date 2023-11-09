package com.poly.service.Impl;

import com.poly.dto.BillProRes;
import com.poly.dto.SearchBillDto;
import com.poly.entity.Bill;
import com.poly.entity.BillStatus;
import com.poly.entity.Product;
import com.poly.repository.BillRepos;
import com.poly.repository.BillStatusRepos;
import com.poly.entity.BillProduct;
import com.poly.entity.Product;
import com.poly.repository.*;
import com.poly.service.BillService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BillImpl implements BillService {
    @Autowired
    private BillRepos billRepos;
    @Autowired
    private BillProductRepos billProductRepos;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    BillStatusRepos billStatusRepos;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PaymentMethodRepos paymentMethodRepos;

    Random generator = new Random();

    @Override
    public Bill add(BillProRes bill) {

        Bill bi = new Bill();
        bi.setCustomer(bill.getCustomer());
        bi.setCode("HD" + generator.nextInt(10) + 9);
        bi.setCreateDate(new java.util.Date());
        bi.setPaymentDate(new java.util.Date());
        bi.setTotalPrice(bill.getTotalPrice());
        bi.setPaymentStatus(1);
        bi.setBillStatus(billStatusRepos.findByCode("WP").get());
        bi.setPaymentMethod(paymentMethodRepos.findAll().get(0));
//        bi.setVoucher(bill.getVoucher());
//        bi.setNote(bill.getNote());
        return this.billRepos.save(bi);
    }

    @Override
    public void addBillPro(Bill bill, BillProRes billProRes) {
        List<Optional<Product>> list = new ArrayList<>();
        for (Integer id : billProRes.getProduct()) {
            list.add(productRepository.findById(id));
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isPresent()) {
                BillProduct billProduct = new BillProduct();
                billProduct.setBill(bill);
                billProduct.setProduct(list.get(i).get());
                billProduct.setPrice(list.get(i).get().getPrice_export());
                billProduct.setQuantity(billProRes.getQuantity().get(i));
                this.billProductRepos.save(billProduct);
            }
        }
    }
    @Override
    public Page<Bill> loadData(SearchBillDto searchBillDto, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bill> billCriteriaQuery = criteriaBuilder.createQuery(Bill.class);
        Root<Bill> billRoot = billCriteriaQuery.from(Bill.class);

        List<Predicate> list = new ArrayList<Predicate>();
        if (!searchBillDto.getKey().isEmpty()) {
            list.add(criteriaBuilder.or(criteriaBuilder.equal(billRoot.get("code"), searchBillDto.getKey()),
                    criteriaBuilder.equal(billRoot.get("customer").get("name"), searchBillDto.getKey()),
                    criteriaBuilder.equal(billRoot.get("customer").get("phoneNumber"), searchBillDto.getKey())));
        }
        if (searchBillDto.getPaymentStatus() != -1) {
            list.add(criteriaBuilder.equal(billRoot.get("paymentStatus"), searchBillDto.getPaymentStatus()));
        }
        if (!searchBillDto.getBillStatus().isEmpty()) {
            list.add(criteriaBuilder.equal(billRoot.get("billStatus").get("code"), searchBillDto.getBillStatus()));
        }
        if (!searchBillDto.getDate().isEmpty()) {
            String date1 = searchBillDto.getDate().substring(0, searchBillDto.getDate().indexOf("-") - 1).replace("/", "-");
            String date2 = searchBillDto.getDate().substring(searchBillDto.getDate().indexOf("-") + 1, searchBillDto.getDate().length()).replace("/", "-");
            System.out.println(date1 + date2);
            Date dateStart = Date.valueOf(date1.trim());
            Date dateEnd = Date.valueOf(date2.trim());
            list.add(criteriaBuilder.between(billRoot.get("createDate"), dateStart, dateEnd));
        }
        billCriteriaQuery.where(criteriaBuilder.and(list.toArray(new Predicate[list.size()])));
        List<Bill> result = entityManager.createQuery(billCriteriaQuery).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
        List<Bill> result2 = entityManager.createQuery(billCriteriaQuery).getResultList();
        if (pageable.getPageSize() == 1) {
            pageable = PageRequest.of(0, result2.size());
            result = entityManager.createQuery(billCriteriaQuery).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
        }
        Page<Bill> page = new PageImpl<>(result, pageable, result2.size());
        System.out.println(page);
        return page;
    }

    @Override
    public Bill add(Bill bill) {
        return billRepos.save(bill);
    }

    @Override
    public Bill update(Bill bill, Integer id) {
        Optional<Bill> optional = billRepos.findById(id);
        if (optional.isPresent()) {
            Bill billUpdate = optional.get();
            if (bill.getBillStatus() != null) {
                billUpdate.setBillStatus(bill.getBillStatus());
            }
            if (bill.getPaymentMethod() != null) {
                billUpdate.setPaymentMethod(bill.getPaymentMethod());
            }
//            billUpdate.setNote(bill.getNote());
//            billUpdate.setCustomer(bill.getCustomer());
//            billUpdate.setPaymentDate(bill.getPaymentDate());
            if (bill.getPaymentStatus() != -1) {
                billUpdate.setPaymentStatus(bill.getPaymentStatus());
            }
            return billRepos.save(billUpdate);
        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<Bill> optional = billRepos.findById(id);
        if (optional.isPresent()) {
            billRepos.delete(optional.get());
            return true;
        }
        return false;
    }

    @Override
    public Bill getOneById(Integer id) {
        Optional<Bill> optional = billRepos.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Bill getOneByIdCustomer(Integer idCustomer) {
        Optional<Bill> optional = billRepos.getBillByCustomer(idCustomer);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Bill> findAllBillByUser(Integer id) {
        List<Bill> dto= this.billRepos.findBillByUser(id);
   return dto;
    }


}
