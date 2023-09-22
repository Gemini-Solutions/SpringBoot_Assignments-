package com.example.demoSpring.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public Long add(Long value){
        return value+value;
    }
    public Long multiply(Long value){
        return value*value;
    }
    public Long divide(Long value1,Long value2){
        return value1/value2;
    }
    public Long minus(Long value1,Long value2){
        return value1-value2;
    }
    public Long reminder(Long value1,Long value2){
        return value1/value2;
    }

}
