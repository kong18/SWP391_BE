package com.FPTU.controller;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordercourses")
@CrossOrigin("http://127.0.0.1:5173/")
public class OrderCourseController {
    @Autowired
    private OrderCourseService orderCourseService;

    @GetMapping
    public List<OrderCourseDTO> getAllOrderCourses() {
        return orderCourseService.findAll();
    }

    @GetMapping("/{id}")
    public OrderCourseDTO findOrderCourseById(@PathVariable("id") Long id) {
        return orderCourseService.findById(id);
    }

    @PostMapping()
    public OrderCourseDTO addOrderCourse(@RequestBody OrderCourseDTO orderCourseDTO) {
        return orderCourseService.save(orderCourseDTO);
    }

//    @PutMapping("/status")
//    public void updateStatus() {
//        orderCourseService.updateStatus();
//    }
}
