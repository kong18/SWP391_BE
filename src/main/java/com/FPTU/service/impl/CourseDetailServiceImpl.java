package com.FPTU.service.impl;

import com.FPTU.converter.CourseDetailConverter;
import com.FPTU.dto.CourseDetailDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseDetail;
import com.FPTU.repository.CourseDetailRepository;
import com.FPTU.repository.CourseRepository;
import com.FPTU.service.CourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseDetailServiceImpl implements CourseDetailService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseDetailRepository courseDetailRepository;
    @Autowired
    private CourseDetailConverter courseDetailConverter;
    @Override
    public CourseDetailDTO save(CourseDetailDTO courseDetailDTO) {
        CourseDetail courseDetail = new CourseDetail();
        if (courseDetailDTO.getId() != null) {
            CourseDetail oldCourseDetail = courseDetailRepository.getOne(courseDetailDTO.getCourseId());
            courseDetail = courseDetailConverter.toEntity(courseDetailDTO, oldCourseDetail);
        } else {
            courseDetail = courseDetailConverter.toEntity(courseDetailDTO);
        }
        Course course = courseRepository.getOne(courseDetailDTO.getCourseId());
        courseDetail.setCourse(course);
        courseDetail = courseDetailRepository.save(courseDetail);
        return courseDetailConverter.toDTO(courseDetail);
    }
}