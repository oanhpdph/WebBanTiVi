package com.poly.dto;

import com.poly.service.Impl.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Getter
@Setter
@AllArgsConstructor

public class CustomerDto {
  @Autowired
private  CustomerServiceImpl customerService;
    private String email;
    private String password;
}
