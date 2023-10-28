package com.FPTU.service;

import com.FPTU.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> findAll();
    OrderItemDTO save(OrderItemDTO orderItemDTO);
    List<OrderItemDTO> findAllForUser(String username);

    OrderItemDTO findById(Long id);

    List<OrderItemDTO> findOrderItemHistoryForUser(String username);

    String updateStatus(String status, Long id);
}