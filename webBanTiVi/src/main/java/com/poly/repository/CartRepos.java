package com.poly.repository;

import com.poly.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepos extends JpaRepository<Cart, Integer> {
}
