package com.FPTU.repository;

import com.FPTU.model.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {
    List<CourseDetail> findAll();
}