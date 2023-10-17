package com.FPTU.converter;

import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseLevel;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    public Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        if (courseDTO.getLevel().equalsIgnoreCase("BEGINNER")) {
            course.setLevel(CourseLevel.BEGINNER);
        }
        if (courseDTO.getLevel().equalsIgnoreCase("INTERMEDIATE")) {
            course.setLevel(CourseLevel.INTERMEDIATE);
        }
        if (courseDTO.getLevel().equalsIgnoreCase("ADVANCED")) {
            course.setLevel(CourseLevel.ADVANCED);
        }
        return course;
    }
    public CourseDTO toDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setTitle(course.getTitle());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setPrice(course.getPrice());
        if (course.getLevel().equals("BEGINNER")) {
            courseDTO.setLevel("BEGINNER");
        }
        if (course.getLevel().equals("INTERMEDIATE")) {
            courseDTO.setLevel("INTERMEDIATE");
        }
        if (course.getLevel().equals("ADVANCED")) {
            courseDTO.setLevel("ADVANCED");
        }
        return courseDTO;
    }
    public Course toEntity(CourseDTO courseDTO, Course course) {
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        if (courseDTO.getLevel().equalsIgnoreCase("BEGINNER")) {
            course.setLevel(CourseLevel.BEGINNER);
        }
        if (courseDTO.getLevel().equalsIgnoreCase("INTERMEDIATE")) {
            course.setLevel(CourseLevel.INTERMEDIATE);
        }
        if (courseDTO.getLevel().equalsIgnoreCase("ADVANCED")) {
            course.setLevel(CourseLevel.ADVANCED);
        }
        return course;
    }
}