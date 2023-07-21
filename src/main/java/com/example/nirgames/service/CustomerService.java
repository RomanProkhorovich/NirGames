package com.example.nirgames.service;

import com.example.nirgames.model.Customer;
import com.example.nirgames.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public Optional<Customer> findByUsername(String username){
        return customerRepository.findByUsername(username);
    }
    public Optional<Customer> findById(Long id){
        return customerRepository.findById(id);
    }
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
    public void delete(Long id){
        customerRepository.deleteById(id);
    }

}
