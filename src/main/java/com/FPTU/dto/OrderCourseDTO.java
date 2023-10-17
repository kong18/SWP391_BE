package com.FPTU.dto;

import com.FPTU.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCourseDTO {
    private Long id;
    private Long customerId;
    private Long total;
    private String orderDate;
    private Long[] courseId;
    private String status;
    private String paymentMethod;
}