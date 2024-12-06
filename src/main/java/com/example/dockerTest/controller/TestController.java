package com.example.dockerTest.controller;

import com.example.dockerTest.model.Test;
import com.example.dockerTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/test")
public class TestController {

    @Autowired
    private TestService test;

    @GetMapping
    public List<Test> getTest(){
        return test.getTest();
    }

    @PostMapping("/create")
    public String createTest(){
        test.createTest();
        return "Success!!";
    }

}
