package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "history_billProduct")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryBillProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_bill_product")
    private BillProduct billProduct ;

    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @Column(name = "quantity_request_return")
    private Integer quantityRequestReturn;

    @Column(name = "quantity_accept_return")
    private Integer quantityAcceptReturn;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "return_times")
    private Integer returnTimes;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private Integer status;

    @Column(name="note")
    private String note;

    @OneToMany(mappedBy="hBillProduct")
    private List<ImageReturned> listImage;
}
