package com.FPTU.service;

import com.FPTU.dto.CourseDetailDTO;
import org.springframework.stereotype.Service;

@Service
public interface CourseDetailService {
    CourseDetailDTO save(CourseDetailDTO courseDetailDTO);
}