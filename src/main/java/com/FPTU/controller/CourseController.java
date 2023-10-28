package com.FPTU.controller;

import com.FPTU.dto.CourseDTO;
import com.FPTU.exceptions.CourseNotFoundException;
import com.FPTU.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/customer/{username}")
    public List<CourseDTO> findAllByUserId_RoleCustomer(@PathVariable("username") String username) {
        return courseService.findAllByUserId_RoleCustomer(username);
    }

    @GetMapping("/instructor/{username}")
    public List<CourseDTO> findAllByUserId_RoleInstructor(@PathVariable("username") String username) {
        return courseService.findAllByUserId_RoleInstructor(username);
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
    public ResponseEntity<String> addCourse(@RequestBody CourseDTO courseDTO) {
        String message = courseService.save(courseDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PutMapping()
    public ResponseEntity<String> updateCourse(@RequestBody CourseDTO courseDTO) {
        if(!courseService.existsById(courseDTO.getId())) {
            throw new CourseNotFoundException(courseDTO.getId());
        }
        String message = courseService.save(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long id) {
        if(!courseService.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseService.deleteById(id);
        return ResponseEntity.ok("Delete the course with id" + id);
    }



}