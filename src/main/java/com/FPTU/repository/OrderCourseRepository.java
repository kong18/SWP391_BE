package com.FPTU.repository;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.dto.OrderRevenueByMonth;
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
import java.util.Objects;

@Repository
public interface OrderCourseRepository extends JpaRepository<OrderCourse, Long> {
    @Query(value = "SELECT * FROM order_course o ORDER BY o.order_date desc", nativeQuery = true)
    List<OrderCourse> findAllByOrderDateDesc();
    OrderCourse save(OrderCourse orderCourse);
    @Transactional
    @Modifying
    @Query("UPDATE OrderCourse o SET o.status = :newStatus")
    void updateStatus(@Param("newStatus") String newStatus);

    @Query(value = "SELECT MONTH(o.order_date) AS month, YEAR(o.order_date) AS year, SUM(o.total) AS revenue\n" +
            "from order_course o\n" +
            "GROUP BY YEAR(o.order_date), MONTH(o.order_date)\n" +
            "ORDER BY YEAR(o.order_date) DESC, MONTH(o.order_date) DESC", nativeQuery = true)
    List<Object[]> getMonthlyRevenue();

    List<OrderCourse> findByUser_UserId(Long userId);


}
