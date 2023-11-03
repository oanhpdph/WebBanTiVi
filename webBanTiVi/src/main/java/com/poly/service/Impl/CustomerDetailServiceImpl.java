package com.poly.service.Impl;

import com.poly.config.CustomerUserDetail;
import com.poly.entity.Customer;
import com.poly.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerDetailServiceImpl implements UserDetailsService {

    private  final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> userInfo = customerRepository.findByUsername(username) ;
        return userInfo.map(CustomerUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
