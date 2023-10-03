package com.FPTU.repository;

import com.FPTU.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();

    @Query("SELECT c FROM Course c WHERE c.title LIKE %:name%")
    List<Course> findByNameContainingIgnoreCase(String name);
}