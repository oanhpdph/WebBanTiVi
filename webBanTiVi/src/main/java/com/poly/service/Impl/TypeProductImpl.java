package com.poly.service.Impl;

import com.poly.entity.GroupProduct;
import com.poly.entity.TypeProduct;
import com.poly.repository.GroupProductRepo;
import com.poly.repository.TypeProductRepo;
import com.poly.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeProductImpl implements TypeProductService {

    @Autowired
    private TypeProductRepo typeProductRepo;

    @Autowired
    private GroupProductRepo groupProductRepo;

    @Override
    public List<TypeProduct> findAll() {
        return typeProductRepo.findAll();
    }

    @Override
    public TypeProduct findById(Integer id) {
        return typeProductRepo.findById(id).get();
    }

    @Override
    public List<TypeProduct> findByGroupProduct(Integer idGroup) {
        Optional<GroupProduct> optional = groupProductRepo.findById(idGroup);
        if (optional.isPresent()) {
            return typeProductRepo.findByGroupProduct(optional.get());
        }
        return null;
    }
}
