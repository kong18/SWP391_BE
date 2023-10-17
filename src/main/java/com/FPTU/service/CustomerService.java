package com.FPTU.service;

import com.FPTU.converter.CustomerConverter;
import com.FPTU.dto.CustomerDTO;
import com.FPTU.model.Customer;
import com.FPTU.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public CustomerDTO getCustomerByUserName(String name) {
        return customerConverter.toDTO(customerRepository.getCustomerByUserName(name));
    }
}