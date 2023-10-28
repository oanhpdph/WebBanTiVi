package com.poly.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    @NotEmpty(message = "Please enter valid name.")
    private String username;

    @NotEmpty(message = "Please enter valid password.")
    private String password;

}
