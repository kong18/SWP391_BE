package com.FPTU.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orderItem",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetailItem> orderDetailItems = new ArrayList<>();

    private String orderDate;

    private long total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    private String address;

    private String phoneNumber;

    private String paymentMethod;
}