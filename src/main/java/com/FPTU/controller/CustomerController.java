package com.FPTU.controller;


import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.dto.CommentDTO;
import com.FPTU.dto.CustomerDTO;
import com.FPTU.model.Customer;
import com.FPTU.service.CommentService;
import com.FPTU.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://127.0.0.1:5173/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public CustomerDTO getCustomerByUserName(@RequestBody AuthenticatedUserDto authenticatedUserDto) {
        return customerService.getCustomerByUserName(authenticatedUserDto.getUsername());
    }

}