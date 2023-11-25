package com.poly.dto;

import com.poly.entity.Bill;
import com.poly.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillProRes {

    List<Integer> product;
    Bill bill;
    Integer qty;
    @NotEmpty(message = "Không được để trống")
//    @Size(message = "Hãy nhập tên của bạn!")
    String name;
    @NotEmpty(message = "Không được để trống")
//    @Size(min = 2, max = 30, message = "Hãy nhập địa chỉ của bạn!")
    String address;
    @NotEmpty(message = "Không được để trống")
//    @Size(min = 10, max = 10, message = "Hãy nhập số điện thoại của bạn!")
    String phoneNumber;
    @NotEmpty(message = "Không được để trống")
    @Email(message = "Nhập email hợp lệ!")
    String email;
    BigDecimal totalPrice;
    Integer paymentMethod;
    Integer voucher;
    List<BigDecimal> reducedMoney;
    Users customer;
    List<Integer> quantity;
    //delivery
    String deliver;
    Date deliveryDate;
    Date receivedDate;
    BigDecimal deliveryFee;
    String note;
    Integer status;
}
