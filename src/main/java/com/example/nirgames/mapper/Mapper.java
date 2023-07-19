package com.example.nirgames.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<T, E> {
    E map(T obj);
}
