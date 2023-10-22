package com.poly.entity;

import com.poly.entity.idClass.VoucherCustomerId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "voucher_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(VoucherCustomerId.class)
@Builder
public class VoucherCustomer {

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
    private Date dateStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name="active")
    private boolean active;

}
