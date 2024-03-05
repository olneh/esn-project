package com.example.esnproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("hello")
    public String sayHello(){
        return "Hello";
    }
}
