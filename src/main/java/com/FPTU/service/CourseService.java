package com.FPTU.service;


import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;

import java.util.List;


public interface CourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO save(CourseDTO courseDTO);
    List<CourseDTO> searchCourses(String title); // Update the parameter name
    boolean deleteCourseById(Long id);
}
