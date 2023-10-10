package com.poly.service;

import com.poly.entity.Cart;
import com.poly.repository.CartRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CartService {
    public List<Cart> getAll();

    public void add(Cart cart);

    public void delete(Integer id);

    public Cart edit(Integer id);
}
