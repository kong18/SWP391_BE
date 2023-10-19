package com.FPTU.service;

import com.FPTU.dto.CourseDetailDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseDetailService {
    CourseDetailDTO save(CourseDetailDTO courseDetailDTO);
    List<CourseDetailDTO> findAllByCourseId(Long courseId);

    boolean existsById(Long id);

    void deleteById(Long id);
}
