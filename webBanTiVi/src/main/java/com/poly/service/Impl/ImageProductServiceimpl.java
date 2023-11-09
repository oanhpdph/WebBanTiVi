package com.poly.service.Impl;

import com.poly.entity.ImageProduct;
import com.poly.repository.ImageProductRepository;
import com.poly.service.ImageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageProductServiceimpl implements ImageProductService {
    @Autowired
    ImageProductRepository repository;
    @Override
    public void add(ImageProduct imageProduct) {
        repository.saveAndFlush(imageProduct);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Integer id, ImageProduct imageProduct) {
        ImageProduct image = getOne(id);
        image.setNameImage(imageProduct.getNameImage());
        image.setLocation(imageProduct.getProduct().getActive());
        image.setProduct(imageProduct.getProduct());
        repository.flush();
    }

    @Override
    public List<ImageProduct> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ImageProduct> getByProductDetailId(Integer id) {
        return repository.findByProductDetailId(id);
    }

    public ImageProduct getOne(Integer id) {
        return repository.findById(id).get();
    }
}
