package com.FPTU.controller;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordercourses")
@CrossOrigin("http://127.0.0.1:5173/")
public class OrderCourseController {
    @Autowired
    private OrderCourseService orderCourseService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<OrderCourseDTO> getAllOrderCourses() {
        return orderCourseService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public OrderCourseDTO findOrderCourseById(@PathVariable("id") Long id) {
        return orderCourseService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public OrderCourseDTO addOrderCourse(@RequestBody OrderCourseDTO orderCourseDTO) {
        return orderCourseService.save(orderCourseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void updateStatus(@PathVariable("id") Long orderId, @RequestBody Map<String, String> request) {
        String newStatus = request.get("newStatus");
        orderCourseService.updateStatus(orderId, newStatus);
    }
}