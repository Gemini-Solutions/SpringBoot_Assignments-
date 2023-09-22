package com.example.demoSpring.controller;
import com.example.demoSpring.dto.VehicleDto;
import com.example.demoSpring.models.VehicleModel;
import com.example.demoSpring.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@RestController
@ResponseBody
@RequestMapping("/v1")
public class VehicleController {
//
    @Autowired
    public VehicleService vehicleService;
    private VehicleModel vehicleModel;

    private VehicleDto vehicleDto;

    @PostMapping("/addDetails")
    public ResponseEntity<Object> createDetails(@RequestBody @Valid VehicleDto dto){
        System.out.println(dto.toString());
        return vehicleService.saveDetails(dto);
    }
    @PutMapping("/addDetails")
    public ResponseEntity<Object> updateDetails(@RequestParam String id, @RequestBody @Valid VehicleDto dto){
        System.out.println(dto.toString());
        return vehicleService.updateDetails(id,dto);
    }
    @GetMapping("/addDetails")
    public ResponseEntity<Optional<VehicleModel>> findVehicleDetailsById(@RequestParam String id) {
        return vehicleService.findVehicleDetailsById(id);
    }
    @GetMapping("/getModels")
    public ResponseEntity<List<VehicleModel>> findVehicleDetailsByModel(@RequestParam String model) {
        return vehicleService.findDetailsByModel(model);
    }
    @GetMapping("/getYear")
    public ResponseEntity<List<VehicleModel>> findVehicleDetailByYear(@RequestParam Long year) {
        return vehicleService.findVehicleDetailsByYear(year);
    }
    @GetMapping("/checkNumber")
    public Boolean checkNumber(@RequestParam String number) {
        boolean b1= Pattern.compile("^[A-Za-z]{2}\\s*\\d{2}[A-Za-z]\\s*\\d{4}$").matcher(number).matches();
        boolean b2 = Pattern.compile("^\\d{2}\\s*[A-Za-z]{2}\\s*\\d{4}\\s*[A-Za-z]{2}$").matcher(number).matches();
        if(b1 || b2){
        return true;}
        else {return false;}
    }
    }



