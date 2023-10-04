package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_customer")
    private Customer customer;

    @Column(name="code")
    private String code;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="payment_date")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name="status")
    private BillStatus billStatus;

    @ManyToOne
    @JoinColumn(name="payment_method")
    private PaymentMethod paymentMethod;

    @Column(name="note")
    private String note;
}
