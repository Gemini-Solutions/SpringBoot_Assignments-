package com.example.demo_apis.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class EmployeModel {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long empId;

    @NotEmpty @NotNull
    private String name;

    @NotEmpty @NotNull @Email
    private String email;

    @NotNull @NotEmpty
    private String location;

    @NotNull
    private Long salary;

    @NotNull
    private Long age;

    @NotNull
    private Long phoneNumber;

}
