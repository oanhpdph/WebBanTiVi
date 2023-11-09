package com.poly.config;


import com.poly.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HachNV on 29/05/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffUserDetail implements UserDetails {
    private int id;

    private String name;

    private String password;

    private String roles;

    private String avatar;

    private String phone;

    private String email;

    private String address;

    private Date birthday;

    private boolean gender;

    private List<GrantedAuthority> authorities;

    public StaffUserDetail(Staff staff) {
        id=staff.getId();
        name = staff.getUsername();
        password = staff.getPassword();
        avatar=staff.getAvatar();
        roles=staff.getRoles();
        phone=staff.getPhone();
        email=staff.getEmail();
        address=staff.getAddress();
        birthday=staff.getBirthday();
        gender =staff.isGender();
        authorities = Arrays.stream(staff.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    public String getAvatar(){
        return avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}