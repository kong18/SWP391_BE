package com.FPTU.converter;

import com.FPTU.dto.OrderItemDTO;
import com.FPTU.model.OrderItem;
import com.FPTU.model.Status;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {
    public OrderItem toEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderDate(orderItemDTO.getOrderDate());
        orderItem.setTotal(orderItemDTO.getTotal());
        orderItem.setStatus(Status.PROCESSING);
        orderItem.setAddress(orderItemDTO.getAddress());
        orderItem.setPhoneNumber(orderItemDTO.getPhoneNumber());
        orderItem.setPaymentMethod("Paypal");
        return orderItem;
    }
    public OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getOrderId());
        orderItemDTO.setCustomerId(orderItem.getCustomer().getCustomerId());
        orderItemDTO.setOrderDate(orderItem.getOrderDate());
        orderItemDTO.setTotal(orderItem.getTotal());
        orderItemDTO.setStatus(orderItem.getStatus().toString());
        orderItemDTO.setAddress(orderItem.getAddress());
        orderItemDTO.setPhoneNumber(orderItem.getPhoneNumber());
        orderItemDTO.setPaymentMethod(orderItem.getPaymentMethod());
        return  orderItemDTO;
    }

}