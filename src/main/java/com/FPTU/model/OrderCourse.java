package com.FPTU.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OrderCourse")
public class OrderCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orderCourse",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetailCourse> orderDetailCourses = new ArrayList<>();

    private String orderDate;

    private Long total;

    private String status;

    private String paymentMethod;
}