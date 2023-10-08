package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name="staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="gender")
    private boolean gender;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="address")
    private String  address;

    @Column(name="email")
    private String  email;

    @Column(name="phone")
    private String  phone;

    @Column(name="password")
    private String  password;

    @Column(name="active")
    private boolean active;

    @Column(name="position")
    private int position;

    @Column(name="avatar")
    private String avatar;

}
