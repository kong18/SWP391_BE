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

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "instructor_id", nullable = false, referencedColumnName = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetailCourse> orderDetailCourses = new ArrayList<>();

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CourseDetail> courseDetails = new ArrayList<>();

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "category_id")
    private CourseCategory courseCategory;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CourseDiscount> courseDiscounts = new ArrayList<>();

    private String title;
    private String description;
    private Long price;
    private String img;
    private String createdDate;
    private Long duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private CourseLevel level;


}