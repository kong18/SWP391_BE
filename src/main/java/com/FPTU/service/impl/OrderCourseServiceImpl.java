package com.FPTU.service.impl;

import com.FPTU.converter.OrderCourseConverter;
import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.model.OrderCourse;
import com.FPTU.model.OrderDetailCourse;
import com.FPTU.model.User;
import com.FPTU.repository.CourseRepository;
import com.FPTU.repository.OrderCourseRepository;
import com.FPTU.repository.OrderDetailCourseRepository;
import com.FPTU.repository.UserRepository;
import com.FPTU.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderCourseServiceImpl implements OrderCourseService {

    @Autowired
    private OrderCourseRepository orderCourseRepository;
    @Autowired
    private OrderCourseConverter orderCourseConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private OrderDetailCourseRepository orderDetailCourseRepository;

    @Override
    public List<OrderCourseDTO> findAll() {
        List<OrderCourse> list = orderCourseRepository.findAll();
        List<OrderCourseDTO> listDTO = new ArrayList<>();
        for (OrderCourse o : list) {
            OrderCourseDTO orderCourseDTO = orderCourseConverter.toDTO(o);
            listDTO.add(orderCourseDTO);
        }
        return listDTO;
    }

    @Override
    public OrderCourseDTO save(OrderCourseDTO orderCourseDTO) {
        OrderCourse orderCourse = new OrderCourse();
        orderCourse = orderCourseConverter.toEntity(orderCourseDTO);

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = now.format(formatter);
        orderCourse.setOrderDate(formattedDateTime);

        User user = userRepository.getOne(orderCourseDTO.getUserId());
        orderCourse.setUser(user);
        orderCourse = orderCourseRepository.save(orderCourse);
        for (Long c : orderCourseDTO.getCourseId()) {
            OrderDetailCourse orderDetailCourse = new OrderDetailCourse();
            orderDetailCourse.setCourse(courseRepository.getOne(c));
            orderDetailCourse.setOrderCourse(orderCourseRepository.getOne(orderCourse.getOrderId()));
            orderDetailCourseRepository.save(orderDetailCourse);
        }
        return orderCourseConverter.toDTO(orderCourse);
    }

    @Override
    public OrderCourseDTO findById(Long id) {
        return orderCourseConverter.toDTO(orderCourseRepository.getOne(id));
    }

    @Override
    @Transactional
    public void updateStatus(Long orderId, String newStatus) {
        // Implement the logic to update the status based on orderId and newStatus
        OrderCourse orderCourse = orderCourseRepository.findById(orderId).orElse(null);
        if (orderCourse != null) {
            orderCourse.setStatus(newStatus);
            orderCourseRepository.save(orderCourse);
        }
    }
}