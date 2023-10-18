package com.FPTU.service;

import com.FPTU.dto.OrderCourseDTO;

import java.util.List;

public interface OrderCourseService {
    List<OrderCourseDTO> findAll();
    OrderCourseDTO save(OrderCourseDTO orderCourseDTO);

    OrderCourseDTO findById(Long id);

//    void updateStatus();
}
