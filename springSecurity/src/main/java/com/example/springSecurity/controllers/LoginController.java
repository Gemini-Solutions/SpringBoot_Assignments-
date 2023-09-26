package com.example.springSecurity.controllers;

import com.example.springSecurity.dto.LoginDto;
import com.example.springSecurity.dto.UserDto;
import com.example.springSecurity.models.UserModel;
import com.example.springSecurity.services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginServices loginServices;

    @PostMapping("/signup")
    private ResponseEntity<Object> signup(@RequestBody  LoginDto dto){
        return loginServices.signup(dto);
    }

    @PostMapping("/signin")
    private ResponseEntity<Object> signin(@RequestBody UserDto dto){
        return loginServices.signin(dto);
    }


    @GetMapping("/alluser")
    private List<UserModel> getAllUser(){
        return loginServices.getAllUsers();
    }
}
