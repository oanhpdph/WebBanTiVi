package com.poly.service.Impl;

import com.poly.entity.Product;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> findAllByAll(Integer idBrand, Integer idColor, Integer idOrigin, Integer idReso, Integer idSize,
                                      Integer idType, int min, int max){
        return productRepository.findAllByAll(idBrand,idColor,idOrigin,idReso,idSize,idType,min,max);
    }
    public List<Product> findAllByKeywordAndPrice(String keyword,int min,int max){
        return productRepository.findAllByKeyWordAndPrice(keyword,min,max);
    }
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(productRepository.findById(id).get());
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

}
