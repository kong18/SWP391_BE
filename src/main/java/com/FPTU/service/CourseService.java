package com.FPTU.service;


import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;

import java.util.List;


public interface CourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO save(CourseDTO courseDTO);
    List<CourseDTO> searchCourses(String name);
}