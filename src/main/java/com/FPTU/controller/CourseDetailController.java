package com.FPTU.controller;

import com.FPTU.dto.CourseDetailDTO;
import com.FPTU.service.CourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/course")
public class CourseDetailController {
    @Autowired
    private CourseDetailService courseDetailService;

    @PostMapping("/detail")
    public ResponseEntity<CourseDetailDTO> addCourseDetail(@RequestBody CourseDetailDTO courseDetailDTO) {
        courseDetailDTO = courseDetailService.save(courseDetailDTO);
        return  ResponseEntity.ok(courseDetailDTO);
    }
    @PutMapping("detail/{id}")
    public ResponseEntity<CourseDetailDTO> updateCourseDetail(@RequestBody CourseDetailDTO courseDetailDTO, @PathVariable("id") Long id) {
        courseDetailDTO.setId(id);
        courseDetailService.save(courseDetailDTO);
        return ResponseEntity.ok(courseDetailDTO);
    }
}