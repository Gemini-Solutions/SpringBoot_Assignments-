package com.example.springSecurity.services;

import com.example.springSecurity.dto.LoginDto;
import com.example.springSecurity.dto.UserDto;
import com.example.springSecurity.models.UserModel;
import com.example.springSecurity.repo.LoginRepo;
import com.example.springSecurity.security.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServices {

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MongoOperations mongoOperations;



    public List<UserModel> getAllUsers(){
        return loginRepo.findAll();
    }
    public ResponseEntity<Object> signup(LoginDto dto){

        if(loginRepo.existsByUsername(dto.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username already exist");
        }
        if(loginRepo.existsByEmail(dto.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email already exist");
        }
        UserModel data = modelMapper.map(dto,UserModel.class);
        return ResponseEntity.ok(loginRepo.save(data));
    }
    public ResponseEntity<Object> signin(UserDto dto){

        UserModel data = loginRepo.findByUsername(dto.getUsername());
        System.out.println("dto"+ dto);
        System.out.println(data.toString());
        if(data.getPassword().equals(dto.getPassword())) {
            String token=jwtUtils.generateTokenFromUsername(dto.getUsername());
//            System.out.println(token);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signin Unsuccessful");
        }
    }
}
