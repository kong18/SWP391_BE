package com.FPTU.service;

import com.FPTU.dto.CommentDTO;
import com.FPTU.dto.CourseDiscountDTO;

import java.util.List;


public interface CommentService {
    CommentDTO save(CommentDTO commentDTO);

    List<CommentDTO> getCommentsByCourseId(Long id);
}
