package com.poly.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

@Data
public class BillDto {
    Integer id;
    String code;
    String customer;
    BigDecimal totalPrice;
    Date createDate;
    String status;

    public BillDto getDto(Map<String, Object> map) {
        BillDto billDto = new BillDto();
        billDto.setCode((String) map.get("code"));
        billDto.setId((Integer) map.get("id"));
        billDto.setCustomer((String) map.get("name"));
        billDto.setStatus((String) map.get("status"));
        billDto.setTotalPrice((BigDecimal) map.get("totalPrice"));
        billDto.setCreateDate((Date) map.get("create_date"));
        return billDto;
    }
}
