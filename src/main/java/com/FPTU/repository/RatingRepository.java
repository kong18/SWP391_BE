package com.FPTU.repository;

import com.FPTU.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query(value = "Select avg(rating) from rating where course_id = :value", nativeQuery = true)
    Double findAverageRating(@Param("value") Long value);

    List<Rating> findByCourse_CourseId(Long courseId);
}
