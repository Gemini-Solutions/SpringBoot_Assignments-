package com.example.springSecurity.models;

import com.example.springSecurity.dto.Urole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class UserModel {
    @Id
    private String id;

    @NotNull @NotBlank
    private String username;

    @NotNull @Email
    private String email;

    @NotBlank @NotNull
    private String password;

    @NotNull
    private Urole role;

}
