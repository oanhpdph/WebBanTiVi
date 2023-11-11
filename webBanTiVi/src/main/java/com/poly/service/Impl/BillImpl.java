package com.poly.service.Impl;

import com.poly.common.RandomNumber;
import com.poly.dto.BillProRes;
import com.poly.dto.SearchBillDto;
import com.poly.entity.Bill;
import com.poly.entity.BillProduct;
import com.poly.entity.ProductDetail;
import com.poly.repository.*;
import com.poly.service.BillService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    ProductDetailRepo productDetailRepo;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PaymentMethodRepos paymentMethodRepos;

    Random generator = new Random();

    @Override
    public Bill add(BillProRes bill) {
        String code = "";
        do {
            code = RandomNumber.generateRandomString(10);
        } while (billRepos.findByCode(code) == null);
        Bill bi = new Bill();
        bi.setCustomer(bill.getCustomer());
        bi.setCode("HD" + code);
        bi.setCreateDate(new java.util.Date());
        bi.setPaymentDate(new java.util.Date());
        bi.setTotalPrice(bill.getTotalPrice());
        bi.setPaymentStatus(1);
        bi.setBillStatus(billStatusRepos.findByCode("WP").get());
        bi.setPaymentMethod(paymentMethodRepos.findAll().get(0));
        return this.billRepos.save(bi);
    }

    @Override
    public void addBillPro(Bill bill, BillProRes billProRes) {
        List<Optional<ProductDetail>> list = new ArrayList<>();
        for (Integer id : billProRes.getProduct()) {
            list.add(productDetailRepo.findById(id));
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isPresent()) {
                BillProduct billProduct = new BillProduct();
                billProduct.setBill(bill);
                billProduct.setProduct(list.get(i).get());
                billProduct.setPrice(list.get(i).get().getPriceExport());
                billProduct.setQuantity(list.get(i).get().getQuantity());
                this.billProductRepos.save(billProduct);
            }
        }
    }

    @Override
    public Page<Bill> loadData(SearchBillDto searchBillDto, Pageable pageable) {
        List<String> billStatus = new ArrayList<>();
        if (searchBillDto.getBillStatus().equals("cho")) {
            billStatus.add("WP");
        }
        if (searchBillDto.getBillStatus().equals("chuanbi")) {
            billStatus.add("PG");
        }
        if (searchBillDto.getBillStatus().equals("danggiao")) {
            billStatus.add("DE");
        }
        if (searchBillDto.getBillStatus().equals("hoanthanh")) {
            billStatus.add("CO");
        }
        if (searchBillDto.getBillStatus().equals("donhuy")) {
            billStatus.add("SC");
            billStatus.add("CC");
        }
        if (searchBillDto.getBillStatus().equals("trahang")) {
            billStatus.add("RR");
            billStatus.add("RE");
            billStatus.add("WR");
        }

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
            for (String s : billStatus) {
                list.add(criteriaBuilder.equal(billRoot.get("billStatus").get("code"), s));
            }
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
        List<Bill> dto = this.billRepos.findBillByUser(id);
        return dto;
    }

    @Override
    public List<Bill> findBillReturn(String code) {
        return this.billRepos.findBillReturn(code);
    }


}
