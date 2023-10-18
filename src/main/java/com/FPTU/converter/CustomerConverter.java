package com.FPTU.converter;

import com.FPTU.dto.CustomerDTO;
import com.FPTU.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getCustomerId());
        customerDTO.setPaymentplf(customer.getPaymentPLF());
        return customerDTO;
    }
}
