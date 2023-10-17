package com.FPTU.dto;

import com.FPTU.model.CourseLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private String img;
    private String level;
    private String createdDate;
    private Long categoryId;
    private Long duration;
    private Double rating;
}