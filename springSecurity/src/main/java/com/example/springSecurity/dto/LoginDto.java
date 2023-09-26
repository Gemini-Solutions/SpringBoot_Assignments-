package com.example.springSecurity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;

@Data
@Configuration
public class LoginDto {
    @Id
    private String id;

    @NotNull
    @NotBlank
    private String username;

    @NotNull @Email
    private String email;

    @NotBlank @NotNull
    private String password;

    @NotNull
    private Urole role;

}
