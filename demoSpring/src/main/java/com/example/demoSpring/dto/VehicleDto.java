package com.example.demoSpring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@Configuration
@ToString
public class VehicleDto {

    @NotNull
    @NotBlank
    private String model;

    @NotNull
    private Long year;

    @NotNull @NotBlank
    private String owner;

    @NotNull @NotBlank
    @Size(min=10)
//    @Pattern(regexp="(^[A-Z\\d]{2}\\s+[\\d]{2}+")",message = "Please Provide a Valid Number")
    private String number;
}
