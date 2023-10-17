package com.FPTU.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id")
    private Long instructorId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, referencedColumnName = "user_id")
    private User user;

    @OneToMany(
        mappedBy = "instructor",
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private List<Course> courses = new ArrayList<>();
}