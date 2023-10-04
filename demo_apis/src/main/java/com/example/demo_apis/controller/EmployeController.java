package com.example.demo_apis.controller;

import com.example.demo_apis.dto.EmployeDto;
import com.example.demo_apis.model.EmployeModel;
import com.example.demo_apis.service.EmployeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employe")
@ResponseBody
public class EmployeController {

    @Autowired
    EmployeService employeService;

    @PostMapping("/createEmp")
    private ResponseEntity<Object> createEmploye(@RequestBody @Valid EmployeDto data){
        return employeService.createEmploye(data);
    }
    @PutMapping("/update")
    private ResponseEntity<Object> updateEmploye(@RequestBody EmployeDto data){
        return employeService.updateEmploye(data);
    }
    @GetMapping("/getEmpDetails")
    private ResponseEntity<List<EmployeModel>> getEmployeDetails(){
        return employeService.getEmployeDetails();
    }

    @GetMapping("/getEmpDetails/single")
    private ResponseEntity<Optional<EmployeModel>> getEmployeDetail(@RequestParam(required = true) Long id){
        return employeService.getEmployeDetail(id);
    }
    @GetMapping("/getEmpDetails/name/location")
    private ResponseEntity<List<EmployeModel>> getEmployefromLocationAndName(@RequestParam String name, @RequestParam String location){
        return employeService.getEmployefromLocationAndName(name,location);
    }
    @DeleteMapping("/deleteUser")
    private ResponseEntity deleteEmpById(@RequestParam(required = true) Long id){
        return employeService.deleteEmpById(id);
    }
    @GetMapping("/getEmpDetails/salary/less")
    private ResponseEntity<List<EmployeModel>> getEmployeSalaryLessAndGreater(@RequestParam Long l, @RequestParam Long g){
        return employeService.getEmployefromLessAndGreater(l,g);
    }
    @GetMapping("/getEmpDetails/name")
    private ResponseEntity<EmployeModel> getEmployeDetailbyNameInitials(@RequestParam(required = true) String ini){
        return employeService.getEmployeDetailbyNameInitials(ini);
    }
    @GetMapping("/getEmpDetails/salary/bet")
    private ResponseEntity<List<EmployeModel>> getEmployeSalaryBetween(@RequestParam Long l, @RequestParam Long g){
        return employeService.getEmployeSalaryBetween(l,g);
    }

    @GetMapping("/token")
    private ResponseEntity<String> generateToken(@RequestParam(required = true) String name){
        return employeService.generateToken(name);
    }
    @GetMapping("v1/getEmpDetails/salary/less")
    private ResponseEntity<List<EmployeModel>> getEmployeSalaryLessAndGreaterMongoRepo(@RequestParam Long l, @RequestParam Long g){
        return employeService.getEmployefromLessAndGreaterMongoRepo(l,g);
    }
    @GetMapping("v1/getEmpDetails/salary/bet")
    private ResponseEntity<List<EmployeModel>> getEmployeSalaryBetweenMongoRepo(@RequestParam Long l, @RequestParam Long g){
        return employeService.getEmployeSalaryBetweenMongoRepo(l,g);
    }

}
