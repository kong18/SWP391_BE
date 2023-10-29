package com.FPTU.repository;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseDetail;
import com.FPTU.model.OrderCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderCourseRepository extends JpaRepository<OrderCourse, Long> {
    List<OrderCourse> findAll();
    OrderCourse save(OrderCourse orderCourse);
    @Transactional
    @Modifying
    @Query("UPDATE OrderCourse o SET o.status = :newStatus")
    void updateStatus(@Param("newStatus") String newStatus);

    List<OrderCourse> findByUser_UserId(Long userId);


}
