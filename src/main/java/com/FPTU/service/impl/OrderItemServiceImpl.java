package com.FPTU.service.impl;

import com.FPTU.converter.ItemConverter;
import com.FPTU.converter.OrderCourseConverter;
import com.FPTU.converter.OrderItemConverter;
import com.FPTU.dto.CourseDTO;
import com.FPTU.dto.ItemDTO;
import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.dto.OrderItemDTO;
import com.FPTU.model.*;
import com.FPTU.repository.*;
import com.FPTU.service.OrderCourseService;
import com.FPTU.service.OrderItemService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.mysql.cj.conf.PropertyKey.logger;

@Service

public class OrderItemServiceImpl implements OrderItemService {
    private static final Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemConverter orderItemConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemConverter itemConverter;

    @Autowired
    private OrderDetailItemRepository orderDetailItemRepository;

    @Override
    public List<OrderItemDTO> findAll() {
        List<OrderItem> list = orderItemRepository.findAll();
        return getListDTO(list);
    }

    @Override
    public OrderItemDTO save(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem = orderItemConverter.toEntity(orderItemDTO);

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = now.format(formatter);
        orderItem.setOrderDate(formattedDateTime);

        User user = userRepository.findByUsername(orderItemDTO.getUser().getUsername());
        orderItem.setUser(user);
        orderItem.setAddress(user.getAddress());
        orderItem = orderItemRepository.save(orderItem);
        for (ItemDTO i : orderItemDTO.getItems()) {
            OrderDetailItem orderDetailItem = new OrderDetailItem();
            orderDetailItem.setItem(itemRepository.getOne(i.getId()));
            orderDetailItem.setOrderItem(orderItemRepository.getOne(orderItem.getOrderId()));
            orderDetailItemRepository.save(orderDetailItem);
        }
        return orderItemConverter.toDTO(orderItem);
    }

    @Override
    public OrderItemDTO findById(Long id) {
        OrderItemDTO orderItemDTO = orderItemConverter.toDTO(orderItemRepository.getOne(id));
        List<Item> items = itemRepository.findItemByOrderId(orderItemDTO.getId());
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for (Item c: items) {
            itemsDTO.add(itemConverter.toDTO(c));
        }

        orderItemDTO.setItems(itemsDTO);
        return orderItemDTO;
    }

    @Override
    @Transactional
    public String updateStatus(String status, Long id) {
        logger.info("Received request to update status to: " + status);
        logger.info("Order ID: " + id);

        if (status.equalsIgnoreCase("PROCESSING")) {
            logger.info("Updating status to PROCESSING.");
            orderItemRepository.updateStatus(Status.ON_GOING, id);
            return "Update Success!";
        }
        if (status.equalsIgnoreCase("ON_GOING")) {
            logger.info("Updating status to ON_GOING.");
            orderItemRepository.updateStatus(Status.DELIVERED, id);
            return "Update Success!";
        }
        return "The order with id " + id + " was delivered";
    }

    @Override
    public List<OrderItemDTO> findByUserName(String username) {
        List<OrderItem> list = orderItemRepository.findByUser_UserId(userRepository.findByUsername(username).getUserId());
        return getListDTO(list);
    }

    private List<OrderItemDTO> getListDTO(List<OrderItem> list) {
        List<OrderItemDTO> listDTO = new ArrayList<>();
        for (OrderItem o : list) {
            OrderItemDTO orderItemDTO = orderItemConverter.toDTO(o);

            List<Item> items = itemRepository.findItemByOrderId(orderItemDTO.getId());
            List<ItemDTO> itemsDTO = new ArrayList<>();
            for (Item c: items) {
                itemsDTO.add(itemConverter.toDTO(c));
            }

            orderItemDTO.setItems(itemsDTO);

            listDTO.add(orderItemDTO);
        }
        listDTO.sort(Comparator.comparing(OrderItemDTO::getOrderDate).reversed());
        return listDTO;
    }
}
