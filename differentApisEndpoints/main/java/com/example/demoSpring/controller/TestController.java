package com.example.demoSpring.controller;
import com.example.demoSpring.services.TestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@ResponseBody
@RequestMapping("/v1")
public class TestController{
//
    @Autowired
    public TestService testService;

    @GetMapping(value = "/add")
    public Long addingValues(@RequestParam @Valid Long value){
        return testService.add(value);
    }
    @GetMapping(value = "/mul")
    public Long multiplyingValues(@RequestParam @Valid Long value){
        return testService.multiply(value);
    }
    @GetMapping(value = "/divide")
    public Long divideValues(@RequestParam(required = true) @Valid Long v1,@RequestParam(required = true) @Valid Long v2){
        return testService.divide(v1,v2);
    }
    @GetMapping(value = "/minus")
    public Long minusValues(@RequestParam(required = true) @Valid Long v1,@RequestParam(required = true) @Valid Long v2){
        return testService.minus(v1,v2);
    }
    @GetMapping(value = "/reminder")
    public Long reminderValues(@RequestParam(required = true) @Valid Long v1,@RequestParam(required = true) @Valid Long v2){
        return testService.reminder(v1,v2);
    }
}
