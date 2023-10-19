package com.FPTU.service.impl;

import com.FPTU.converter.OrderCourseConverter;
import com.FPTU.converter.OrderItemConverter;
import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.dto.OrderItemDTO;
import com.FPTU.model.*;
import com.FPTU.repository.*;
import com.FPTU.service.OrderCourseService;
import com.FPTU.service.OrderItemService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemConverter orderItemConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderDetailItemRepository orderDetailItemRepository;

    @Override
    public List<OrderItemDTO> findAll() {
        List<OrderItem> list = orderItemRepository.findAll();
        List<OrderItemDTO> listDTO = new ArrayList<>();
        for (OrderItem o : list) {
            OrderItemDTO orderItemDTO = orderItemConverter.toDTO(o);
            listDTO.add(orderItemDTO);
        }
        return listDTO;
    }

    @Override
    public OrderItemDTO save(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem = orderItemConverter.toEntity(orderItemDTO);

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = now.format(formatter);
        orderItem.setOrderDate(formattedDateTime);

        User user = userRepository.getOne(orderItemDTO.getUserId());
        orderItem.setUser(user);
        orderItem = orderItemRepository.save(orderItem);
        for (Long c : orderItemDTO.getItemId()) {
            OrderDetailItem orderDetailItem = new OrderDetailItem();
            orderDetailItem.setItem(itemRepository.getOne(c));
            orderDetailItem.setOrderItem(orderItemRepository.getOne(orderItem.getOrderId()));
            orderDetailItemRepository.save(orderDetailItem);
        }
        return orderItemConverter.toDTO(orderItem);
    }

    @Override
    public OrderItemDTO findById(Long id) {
        return orderItemConverter.toDTO(orderItemRepository.getOne(id));
    }

//    @Override
//    public String updateStatus(String status, Long id) {
//        if (status.equalsIgnoreCase("PROCESSING")) {
//            orderItemRepository.updateStatus(Status.ON_GOING, id);
//            return "Update Success!";
//        }
//        if (status.equalsIgnoreCase("ON_GOING")) {
//            orderItemRepository.updateStatus(Status.DELIVERED, id);
//            return "Update Success!";
//        }
//        return "The order with id " + id + " was delivered";
//
//    }
}
