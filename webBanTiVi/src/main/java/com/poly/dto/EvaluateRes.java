package com.poly.dto;

import lombok.*;

import java.sql.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EvaluateRes {
     Integer id;
    Integer customer;
    Integer product;
    Date dateCreate;
    Integer point;
    String comment;

}
