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
public class CourseDTO {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private Long price;
    private String img;
    private String level;
    private String createdDate;
    private CourseCategoryDTO category;
    private Long duration;
    private Double rating;
    private List<CommentDTO> comments;
}