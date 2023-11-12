package com.FPTU.controller;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.dto.OrderRevenueByMonth;
import com.FPTU.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @GetMapping("/{id}")
    public OrderCourseDTO findOrderCourseById(@PathVariable("id") Long id) {
        return orderCourseService.findById(id);
    }

    @GetMapping("/history/{username}")
    public List<OrderCourseDTO> findOrderCourseByUserName(@PathVariable("username") String username) {
        return orderCourseService.findByUserName(username);
    }

    @GetMapping("/monthly")
    public List<OrderRevenueByMonth> getMonthlyRevenue() {
        return orderCourseService.getMonthlyRevenue();
    }

    @PostMapping()
    public ResponseEntity<?> addOrderCourse(@RequestBody @Valid OrderCourseDTO orderCourseDTO) {
        if (orderCourseDTO.getTotal() == 0) {
            return ResponseEntity.ok("");
        }
        OrderCourseDTO o = orderCourseService.save(orderCourseDTO);
        return ResponseEntity.ok(o);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable("id") Long orderId) {
        String newStatus = "Old";
        return ResponseEntity.ok(orderCourseService.updateStatus(orderId, newStatus)) ;

    }
}