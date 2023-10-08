package com.poly.service;

import com.poly.entity.CartProduct;
import com.poly.entity.idClass.CartProductId;
import com.poly.repository.CartProductRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartProductService {

    public List<CartProduct> getAll();

    public void save(CartProduct cp);

    public void delete(CartProductId id);

    public CartProduct edit(CartProductId id);
}

