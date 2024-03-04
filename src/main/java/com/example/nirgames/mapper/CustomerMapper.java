package com.example.nirgames.mapper;


import com.example.nirgames.dto.CustomerDto;
import com.example.nirgames.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GameMapper.class})
public interface CustomerMapper {
    Customer map(CustomerDto dto);
    CustomerDto map(Customer dto);
    List<Customer> mapToEntityList(List<CustomerDto> dtos);
    List<CustomerDto> mapToDtosList(List<Customer> games);
}
