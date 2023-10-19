package com.FPTU.converter;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.model.OrderCourse;
import org.springframework.stereotype.Component;

@Component
public class OrderCourseConverter {
    public OrderCourse toEntity(OrderCourseDTO orderCourseDTO) {
        OrderCourse orderCourse = new OrderCourse();
        orderCourse.setOrderDate(orderCourseDTO.getOrderDate());
        orderCourse.setTotal(orderCourseDTO.getTotal());
        orderCourse.setStatus("New");
        orderCourse.setPaymentMethod("Paypal");
        return orderCourse;
    }
    public OrderCourseDTO toDTO(OrderCourse orderCourse) {
        OrderCourseDTO orderCourseDTO = new OrderCourseDTO();
        orderCourseDTO.setId(orderCourse.getOrderId());
        orderCourseDTO.setUserId(orderCourse.getUser().getUserId());
        orderCourseDTO.setOrderDate(orderCourse.getOrderDate());
        orderCourseDTO.setTotal(orderCourse.getTotal());
        orderCourseDTO.setStatus(orderCourse.getStatus());
        orderCourseDTO.setPaymentMethod(orderCourse.getPaymentMethod());
        return  orderCourseDTO;
    }

}
