//package com.poly.service.Impl;
//
//import com.poly.config.StaffUserDetail;
//import com.poly.entity.Customer;
//import com.poly.repository.CustomerRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class StaffDetailServiceImpl implements UserDetailsService {
//
//    private final CustomerRepository repository;
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Customer> userInfo = repository.findByUsername(username) ;
//        return userInfo.map(StaffUserDetail::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
//    }
//
//
//}