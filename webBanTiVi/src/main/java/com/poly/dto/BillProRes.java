package com.poly.dto;

import com.poly.entity.Users;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillProRes {

    List<Integer> product;
    Integer quantity;
    String name;
    String address;
    String phoneNumber;
    String email;
    BigDecimal totalPrice;
    Integer paymentMethod;
    Integer voucher;
    Users customer;
}
