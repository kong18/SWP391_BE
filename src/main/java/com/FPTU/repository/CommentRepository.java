package com.FPTU.repository;

import com.FPTU.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCourse_CourseId(Long courseId);
}
