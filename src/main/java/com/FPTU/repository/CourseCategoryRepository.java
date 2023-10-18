package com.FPTU.repository;

import com.FPTU.model.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
    List<CourseCategory> findAll();
    CourseCategory findByName(String categoryName);
}
