package com.FPTU.controller;

import com.FPTU.converter.CourseConverter;
import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;
import com.FPTU.repository.CourseRepository;
import com.FPTU.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseConverter courseConverter;
    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }
    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')") // Restrict access to INSTRUCTOR role
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        courseDTO = courseService.save(courseDTO);
        return  ResponseEntity.ok(courseDTO);
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable("id") Long id) {
        Optional<Course> existingCourseOptional = courseRepository.findById(id);
        if(!existingCourseOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Course existingCourse = existingCourseOptional.get();
        existingCourse = courseConverter.toEntity(courseDTO,existingCourse);
        existingCourse = courseRepository.save(existingCourse);

        CourseDTO updatedCourseDTO = courseConverter.toDTO(existingCourse);
        return ResponseEntity.ok(updatedCourseDTO);
    }

    @GetMapping("/search")
    public List<CourseDTO> searchCourses(@RequestParam(value = "title", required = false) String title) {
        return courseService.searchCourses(title); // Update the method call
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping("/{id}") // Remove the curly braces from here
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Long id){
        if(courseService.deleteCourseById(id)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}