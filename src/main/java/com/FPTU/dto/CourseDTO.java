package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private Long instructorId;
    private String title;
    private String description;
    private Long price;
    private String level;
    private Long categoryId;
    private Long duration;
}