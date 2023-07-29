package com.example.nirgames.controller;

import com.example.nirgames.dto.CustomerDto;
import com.example.nirgames.exceptions.CustomerAlreadyExistException;
import com.example.nirgames.mapper.Mapper;
import com.example.nirgames.model.Customer;
import com.example.nirgames.repository.RoleRepository;
import com.example.nirgames.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final CustomerService customerService;
    private final Mapper<CustomerDto, Customer> mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new CustomerDto());
        return "reg-form";
    }
    @PostMapping
    public String registration(@ModelAttribute CustomerDto dto){
        if (!customerService.isUniqueCustomer(dto.getUsername())){
            throw new CustomerAlreadyExistException();
        }
        var customer=mapper.map(dto);
        String password = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(password);
        customer.setRoles(Set.of(roleRepository.findByRole("ROLE_ADMIN").get()));
        customerService.save(customer);
        return "reg-ok";
    }
}
