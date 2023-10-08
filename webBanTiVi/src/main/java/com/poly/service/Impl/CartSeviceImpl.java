package com.poly.service.Impl;

import com.poly.entity.Cart;
import com.poly.repository.CartRepos;
import com.poly.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartSeviceImpl implements CartService {
    @Autowired
    CartRepos cartRepos;

    @Override
    public List<Cart> getAll() {
        return cartRepos.findAll();
    }

    @Override
    public void add(Cart cart) {
        cartRepos.save(cart);
    }

    @Override
    public void delete(Integer id) {
        cartRepos.deleteById(id);
    }

    @Override
    public Cart edit(Integer id) {
        return cartRepos.findById(id).get();
    }
}
