package com.FPTU.repository;

import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseDetail;
import com.FPTU.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findByCourseCategory_CategoryId(Long categoryId);

    @Query(value = "SELECT * FROM course WHERE title LIKE %:title%", nativeQuery = true)
    List<Course> findByNameContainingIgnoreCase(@Param("title")String title);

    @Query(value = "Select c.course_id, c.img, instructor_id, title, description, price, category_id, level, duration from Course c \n" +
            "inner join order_detail_course od\n" +
            "on c.course_id = od.course_id\n" +
            "inner join order_course o\n" +
            "on od.order_id = o.order_id\n" +
            "inner join customer cu\n" +
            "on cu.customer_id = o.customer_id\n" +
            "where cu.customer_id = :customerId", nativeQuery = true)
    List<Course> findByCustomerId(@Param("customerId") Long id);
}