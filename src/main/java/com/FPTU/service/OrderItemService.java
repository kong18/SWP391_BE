package com.FPTU.service;

import com.FPTU.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> findAll();
    OrderItemDTO save(OrderItemDTO orderItemDTO);

    OrderItemDTO findById(Long id);

//    String updateStatus(String status, Long id);
}
