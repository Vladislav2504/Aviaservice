package com.example.aviaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;
}
