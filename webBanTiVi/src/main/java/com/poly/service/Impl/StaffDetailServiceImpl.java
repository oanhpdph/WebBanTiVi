package com.poly.service.Impl;

import com.poly.config.StaffUserDetail;
import com.poly.entity.Staff;
import com.poly.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StaffDetailServiceImpl implements UserDetailsService {

    private final  StaffRepository repository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Staff> userInfo = repository.findByUsername(username) ;
        return userInfo.map(StaffUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }


}