package com.example.dockerTest.service;

import com.example.dockerTest.model.Test;
import com.example.dockerTest.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository repo;

    public List<Test> getTest(){
        return repo.findAll().stream().map(
                test ->Test.builder().id(test.getId())
                        .name(test.getName()).build()
        ).collect(Collectors.toList());
    }

    public void createTest(){
        repo.save(Test.builder()
                .name("Test"+Math.random())
                .build());
    }
}
