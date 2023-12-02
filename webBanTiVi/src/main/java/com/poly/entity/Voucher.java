package com.poly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "voucher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Không được để trống")
    @Column(name = "code")
    private String code;

    @NotEmpty(message = "Không được để trống")
    @Column(name = "name_voucher")
    private String nameVoucher;

    @NotEmpty(message = "Không được để trống")
    @Column(name = "value")
    private int value;

    @NotEmpty(message = "Không được để trống")
    @Column(name = "minimum_value")
    private BigDecimal minimumValue;

    @NotEmpty(message = "Không được để trống")
    @Column(name = "quantity")
    private Integer quantity;

    @NotEmpty(message = "Không được để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "start_day")
    private Date startDay;

    @NotEmpty(message = "Không được để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date")
    private Date expirationDate;

    //    @NotEmpty(message = "Không được để trống")
    @Column(name = "active", unique = true)
    private Boolean active;

    @Column(name = "image")
    private String image;


}