package com.FPTU.controller;

import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;
import com.FPTU.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
    @PostMapping()
    @PreAuthorize("hasRole('INSTRUCTOR')") // Restrict access to INSTRUCTOR role
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        courseDTO = courseService.save(courseDTO);
        return  ResponseEntity.ok(courseDTO);
    }
    @PutMapping()
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable("id") Long id) {
        courseDTO.setId(id);
        return ResponseEntity.ok(courseDTO);
    }
}