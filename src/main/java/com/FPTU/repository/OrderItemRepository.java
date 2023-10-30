package com.FPTU.repository;

import com.FPTU.model.OrderItem;
import com.FPTU.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAll();
    OrderItem save(OrderItem orderItem);

    @Modifying
    @Query("UPDATE OrderItem o SET o.status = :status WHERE o.orderId = :id")
    void updateStatus(@Param("status") Status status, @Param("id") Long id);

    List<OrderItem> findByUser_UserId(Long userId);
}