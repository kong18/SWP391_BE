package com.FPTU.service;

import com.FPTU.dto.OrderCourseDTO;

import java.util.List;

public interface OrderCourseService {
    List<OrderCourseDTO> findAll();
    OrderCourseDTO save(OrderCourseDTO orderCourseDTO);

    OrderCourseDTO findById(Long id);

    void updateStatus(Long orderId, String newStatus);

    List<OrderCourseDTO> findByUserName(String username);
}
