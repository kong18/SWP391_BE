package com.FPTU.service;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.dto.OrderRevenueByMonth;

import java.util.List;

public interface OrderCourseService {
    List<OrderCourseDTO> findAll();
    OrderCourseDTO save(OrderCourseDTO orderCourseDTO);

    OrderCourseDTO findById(Long id);

    List<OrderRevenueByMonth> getMonthlyRevenue();

    String updateStatus(Long orderId, String newStatus);

    List<OrderCourseDTO> findByUserName(String username);
}
