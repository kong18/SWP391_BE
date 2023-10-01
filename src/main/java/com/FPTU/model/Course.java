package com.FPTU.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "instructor_id", nullable = false, referencedColumnName = "instructor_id")
    private Instructor instructor;

    @ManyToMany()
    @JsonIgnore
    @JoinTable(name = "OrderDetail",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<OrderCourse> orderCourses = new ArrayList<>();

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private Set<CourseDetail> courseDetails = new HashSet<>();

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private CourseCategory courseCategory;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private CourseDiscount courseDiscount;

    private String title;
    private String description;
    private Long price;
    private Long duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private CourseLevel level;


}