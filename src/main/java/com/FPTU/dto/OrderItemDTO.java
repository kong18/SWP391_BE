package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long userId;
    private Long total;
    private String orderDate;
    private Long[] itemId;
    private String status;
    private String address;
    private String paymentMethod;
    private String phoneNumber;
}
