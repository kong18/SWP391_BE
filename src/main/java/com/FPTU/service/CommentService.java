package com.FPTU.service;

import com.FPTU.dto.CommentDTO;
import com.FPTU.dto.CourseDiscountDTO;


public interface CommentService {
    CommentDTO save(CommentDTO commentDTO);
}