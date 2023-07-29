package com.example.nirgames.service;

import com.example.nirgames.model.Customer;
import com.example.nirgames.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;

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
    public boolean isUniqueCustomer(String username){
        return !customerRepository.existsCustomersByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user=this.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException(String.format("User %s not found", username)));

        var roles=user.getRoles();
        var authorities= roles.stream()
                .map(x->new SimpleGrantedAuthority(x.getRole())).toList();

        return  User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

}
