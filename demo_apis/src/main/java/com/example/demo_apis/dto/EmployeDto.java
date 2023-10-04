package com.example.demo_apis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
//@Configuration
public class EmployeDto {
    @Id
    private Long empId;

    @NotEmpty
    @NotNull(message = "Name should not be null")
    private String name;

    @NotEmpty @NotNull @Email
    private String email;

    @NotNull
    private Long age;

    @NotNull
    private Long salary;

    @NotNull @NotEmpty
    private String location;

    @NotNull
    private Long phoneNumber;

}
