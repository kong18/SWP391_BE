package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;
    private UserDTO user;
    private Long total;
    private String orderDate;
    private List<ItemDTO> items;
    private String status;
    private String address;
    private String paymentMethod;
    private String phoneNumber;
}