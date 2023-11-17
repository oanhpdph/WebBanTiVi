package com.poly.service.Impl;

import com.poly.dto.EvaluateRes;
import com.poly.entity.Evaluate;
import com.poly.entity.Product;
import com.poly.entity.Users;
import com.poly.repository.CustomerRepository;
import com.poly.repository.EvaluateRepos;
import com.poly.repository.ProductDetailRepo;
import com.poly.repository.ProductRepository;
import com.poly.service.EvaluateService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    EvaluateRepos evaluateRepos;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDetailRepo detailRepo;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<Evaluate> getAll(EvaluateRes evaluateRes) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Evaluate> evaluateCriteriaQuery = criteriaBuilder.createQuery(Evaluate.class);
        Root<Evaluate> evaluateRoot = evaluateCriteriaQuery.from(Evaluate.class);
        List<Predicate> list = new ArrayList<Predicate>();

        if (evaluateRes.getProduct() != null) {
            list.add(criteriaBuilder.equal(evaluateRoot.get("product").get("id"),evaluateRes.getProduct()));
        }
        evaluateCriteriaQuery.where(criteriaBuilder.and(list.toArray(new Predicate[list.size()])));

        Pageable pageable = PageRequest.of(evaluateRes.getPage() - 1, evaluateRes.getSize());

        List<Evaluate> result = entityManager.createQuery(evaluateCriteriaQuery).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
        List<Evaluate> result2 = entityManager.createQuery(evaluateCriteriaQuery).getResultList();

        Page<Evaluate> page = new PageImpl<>(result, pageable, result2.size());
        return page;
    }

    @Override
    public void add(EvaluateRes evaluate) {
        Optional<Users> optionalCustomer = customerRepository.findById(evaluate.getCustomer());
        Optional<Product> optionalProduct = productRepository.findById(evaluate.getProduct());
        Evaluate eval = new Evaluate();
        eval.setId(evaluate.getId());
        eval.setCustomer(optionalCustomer.get());
        eval.setProduct(optionalProduct.get());
        eval.setDateCreate(evaluate.getDateCreate());
        eval.setPoint(evaluate.getPoint());
        eval.setComment(evaluate.getComment());
        this.evaluateRepos.save(eval);
    }

    @Override
    public void delete(Integer id) {
        evaluateRepos.deleteById(id);
    }

    @Override
    public Evaluate edit(Integer id) {
        return evaluateRepos.findById(id).get();
    }

    @Override
    public Evaluate findById(Integer id) {
        Optional<Evaluate> evaluate = evaluateRepos.findById(id);
        if (evaluate.isPresent()) {
            return evaluate.get();
        }
        return null;
    }
}
