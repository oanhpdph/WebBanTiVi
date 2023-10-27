package com.poly.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "feature")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name_feature")
    private String namefeature;
}
