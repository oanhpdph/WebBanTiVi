package com.poly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "discount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Không được để trống")
    @Column(name = "code")
    private String code;

    @NotEmpty(message = "Không được để trống")
    @Column(name = "value")
    private String value;
    
    @AssertTrue(message = "Trạng thái không đúng")
    @Column(name = "active")
    private boolean active;

    @Column(name = "image")
    private String image;

    //    @NotEmpty(message = "Không được để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    //    @NotEmpty(message = "Không được để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "date_end")
    private Date dateEnd;

    @OneToMany(mappedBy = "coupon", fetch = FetchType.EAGER)
    private List<ProductDetail> productDetailList;
}