package com.example.aviaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private long userId;
    @NotBlank(message = "Field cant be empty!")
    private String firstName;
    @NotBlank(message = "Field cant be empty!")
    private String lastName;
    @NotBlank(message = "Field cant be empty!")
    private String password;
    @NotBlank(message = "Field cant be empty!")
    private String email;

}
