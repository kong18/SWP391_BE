package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {
    private Long id;
    private Long courseId;
    private String title;
    private String description;
    private String url;
    private String document;

}