package com.FPTU.controller;

import com.FPTU.dto.CourseDTO;
import com.FPTU.dto.CourseDetailDTO;
import com.FPTU.exceptions.CourseDetailNotFoundException;
import com.FPTU.exceptions.CourseNotFoundException;
import com.FPTU.model.Course;
import com.FPTU.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin("http://127.0.0.1:5173/")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long id) {
        if(!courseService.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        return courseService.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<CourseDTO> findAllByUserId_RoleCustomer(@PathVariable("id") Long id) {
        return courseService.findAllByUserId_RoleCustomer(id);
    }

    @GetMapping("/category/{id}")
    public List<CourseDTO> getCourseByCategoryId(@PathVariable("id") Long id) {
        return courseService.findAllByCategoryId(id);
    }

    @GetMapping("/search")
    public List<CourseDTO> searchCourses(@RequestParam(value = "title", required = false) String title) {
        return courseService.findByName(title);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping()
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        courseDTO = courseService.save(courseDTO);
        return  ResponseEntity.ok(courseDTO);
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable("id") Long id) {
        if(!courseService.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseDTO.setId(id);
        courseService.save(courseDTO);
        return ResponseEntity.ok(courseDTO);
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        if(!courseService.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseService.deleteById(id);
        return "Delete the course by id " + id;
    }

}