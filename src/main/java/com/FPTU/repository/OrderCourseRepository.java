package com.FPTU.repository;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.model.CourseDetail;
import com.FPTU.model.OrderCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCourseRepository extends JpaRepository<OrderCourse, Long> {
    List<OrderCourse> findAll();
    OrderCourse save(OrderCourse orderCourse);

//    @Modifying
//    @Query(value = "UPDATE OrderCourse o SET o.status = Old ", nativeQuery = true)
//    void updateStatus();
}
