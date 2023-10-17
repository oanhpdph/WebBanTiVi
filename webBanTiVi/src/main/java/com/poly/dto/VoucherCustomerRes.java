package com.poly.dto;


import lombok.*;

import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoucherCustomerRes {
    Integer customer;
    Integer voucher;
    Date date_start;
    Date date_end;
    Boolean active;

}
