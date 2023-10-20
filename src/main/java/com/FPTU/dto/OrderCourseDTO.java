package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCourseDTO {
    private Long id;
    private Long userId;
    private Long total;
    private String orderDate;
    private Long[] courseId;
    private String status;
    private String paymentMethod;
}