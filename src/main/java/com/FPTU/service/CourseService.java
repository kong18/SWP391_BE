package com.FPTU.service;


import com.FPTU.dto.CourseDTO;

import java.util.List;


public interface CourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO save(CourseDTO courseDTO);

    List<CourseDTO> findAllByCategoryId(Long categoryId);

    boolean existsById(Long id);

    void deleteById(Long id);

    CourseDTO findById(Long id);

    List<CourseDTO> findByName(String title);

    List<CourseDTO> findAllByUserId_RoleCustomer(Long id);
}
