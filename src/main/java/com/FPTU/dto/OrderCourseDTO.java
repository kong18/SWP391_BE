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
public class OrderCourseDTO {
    private Long id;
    private UserDTO user;
    private Long total;
    private String orderDate;
    private List<CourseDTO> courses;
    private String status;
    private String paymentMethod;
}