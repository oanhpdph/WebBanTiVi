package com.poly.service.Impl;

import com.poly.dto.ProductDetailDto;
import com.poly.entity.Product;
import com.poly.repository.CouponRepository;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CouponRepository couponRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(productRepository.findById(id).get());
    }

    @Override
    public List<Product> findAll(ProductDetailDto productDetailDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);
        List<Predicate> list = new ArrayList<Predicate>();
        if (productDetailDto.getSku() != "") {
            list.add(criteriaBuilder.equal(productRoot.get("sku"), productDetailDto.getSku()));
        }
        productCriteriaQuery.where(criteriaBuilder.and(list.toArray(new Predicate[list.size()])));
        List<Product> result = entityManager.createQuery(productCriteriaQuery).getResultList();
        return result;
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product getOne(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findSameProduct(String same) {
        return productRepository.findBySameProduct(same);
    }


}
