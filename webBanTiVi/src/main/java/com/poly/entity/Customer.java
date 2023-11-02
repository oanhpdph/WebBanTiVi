package com.poly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

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

    @NotBlank(message="Username not null")
    @Column(name="username")
    private String username;

    @Column(name="name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name="date")
    private Date birthday;

    @Column(name="address")
    private String address;

    @NotBlank(message="Username not null")
    @Column(name="phone_number")
    private String phoneNumber;

    @NotBlank(message="Username not null")
    @Column(name="email")
    private String email;

    @NotBlank(message="Username not null")
    @Column(name = "password")
    private String password;

    @Column(name="gender")
    private boolean gender;

    @Column(name="id_card")
    private String idCard;

    @Column(name="avatar")
    private String avatar;

    @Column(name="status")
    private boolean status;

    @Column(name="role")
    private String roles;
//
//    @OneToMany(mappedBy = "customer")
//    private List<Bill> listBill;

}

