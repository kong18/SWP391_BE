package com.FPTU.service.impl;

import com.FPTU.converter.CourseConverter;
import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseCategory;
import com.FPTU.model.Instructor;
import com.FPTU.repository.CourseCategoryRepository;
import com.FPTU.repository.CourseRepository;
import com.FPTU.repository.InstructorRepository;
import com.FPTU.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseConverter courseConverter;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.getId() != null) {
            Course oldCourse = courseRepository.getOne(courseDTO.getId());
            course = courseConverter.toEntity(courseDTO, oldCourse);
        } else {
            course = courseConverter.toEntity(courseDTO);
        }
        CourseCategory courseCategory = courseCategoryRepository.getOne(courseDTO.getCategoryId());
        Instructor instructor = instructorRepository.getOne(courseDTO.getInstructorId());
        course.setCourseCategory(courseCategory);
        course.setInstructor(instructor);
        course = courseRepository.save(course);
        return courseConverter.toDTO(course);
    }


}