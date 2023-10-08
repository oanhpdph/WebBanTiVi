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

    @Temporal(TemporalType.DATE)
    @Column(name="create_date")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name="payment_date")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name="id_status")
    private BillStatus billStatus;

    @ManyToOne
    @JoinColumn(name="id_paymentmethod")
    private PaymentMethod paymentMethod;

    @Column(name="note")
    private String note;
}
