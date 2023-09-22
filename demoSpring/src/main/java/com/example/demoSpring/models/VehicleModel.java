package com.example.demoSpring.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data

public class VehicleModel {

    @Id
    private String id;

    @NotNull @NotBlank
    private String model;

    @NotNull
    private Long year;

    @NotNull @NotBlank
    private String owner;

    @NotNull @NotBlank
    @Size(min=10)
//   @Pattern(regexp="^[A-ZA-Z]{2}|^[0-9]{2}")
    private String number;
}
