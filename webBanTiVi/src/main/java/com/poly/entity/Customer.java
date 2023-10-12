package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name="date")
    private Date birthday;

    @Column(name="address")
    private String address;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="gender")
    private boolean gender;

    @Column(name="id_card")
    private String idCard;

    @Column(name="avatar")
    private String avatar;

    @Column(name="status")
    private int status;

    @OneToMany(mappedBy = "customer")
    private List<Bill> listBill;

}

