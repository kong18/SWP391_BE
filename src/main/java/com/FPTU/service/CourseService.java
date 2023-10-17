package com.FPTU.service;


import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface CourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO save(CourseDTO courseDTO);

    List<CourseDTO> findAllByCategoryId(Long categoryId);

    boolean existsById(Long id);

    void deleteById(Long id);

    CourseDTO findById(Long id);

    List<CourseDTO> findByName(String title);

    List<CourseDTO> findByCustomerId(Long id);
}