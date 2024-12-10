package com.example.dockerTest.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Getter
public class Mapper {

    ModelMapper modelMapper = new ModelMapper();

    public Mapper() {
        log.info("Mapper initialized");
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(elment -> modelMapper.map(elment,targetClass))
                .collect(Collectors.toList());
    }

    public  <D> D map(Object source, Class<D> destinationType) {
        return this.modelMapper.map(source, destinationType);
    }
}
