package com.poly.entity;

import com.poly.entity.idClass.VoucherCustomerId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "voucher_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(VoucherCustomerId.class)
public class Voucher_Customer {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date date_start;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end")
    private Date date_end;

    @Column(name="active")
    private Boolean active;
}
