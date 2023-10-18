package com.FPTU.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", unique = true, referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<OrderCourse> orderCourses = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Comment> comments = new ArrayList<>();

    private String paymentPLF;

    private String img;

    private String address;

}
