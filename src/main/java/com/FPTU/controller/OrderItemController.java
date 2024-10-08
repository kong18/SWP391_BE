package com.FPTU.controller;

import com.FPTU.dto.OrderItemDTO;
import com.FPTU.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
@CrossOrigin("http://127.0.0.1:5173/")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<OrderItemDTO> getAllOrderCourses() {
        return orderItemService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public OrderItemDTO findOrderItemById(@PathVariable("id") Long id) {
        return orderItemService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public OrderItemDTO addOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.save(orderItemDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public String updateStatus(@PathVariable("id") Long id, @RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.updateStatus(orderItemDTO.getStatus(), id);
    }
}