package com.poly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Code is not null")
    @Column(name="code")
    private String code;

    @NotBlank(message = "Name is not null")
    @Column(name="name")
    private String name;

    @Column(name="gender")
    private boolean gender;

    @Temporal(TemporalType.DATE)
    @Column(name="birthday")
    private Date birthday;

    @NotBlank(message = "Name is not null")
    @Column(name="address")
    private String  address;


    @NotBlank(message = "Email is not null")
    @Column(name="email")
    private String  email;

    @NotBlank(message = "Phone is not null")
    @Column(name="phone")
    private String  phone;

    @NotBlank(message = "Password is not null")
    @Column(name="password")
    private String  password;

    @Column(name="active")
    private boolean active;

    @Column(name="position")
    private boolean position;

    @Column(name="avatar")
    private String avatar;

}
