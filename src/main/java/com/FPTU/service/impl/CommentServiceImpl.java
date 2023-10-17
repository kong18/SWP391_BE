package com.FPTU.service.impl;

import com.FPTU.converter.CommentConverter;
import com.FPTU.converter.CourseDiscountConverter;
import com.FPTU.dto.CommentDTO;
import com.FPTU.dto.CourseDiscountDTO;
import com.FPTU.dto.RatingDTO;
import com.FPTU.model.*;
import com.FPTU.repository.CommentRepository;
import com.FPTU.repository.CourseDiscountRepository;
import com.FPTU.repository.CourseRepository;
import com.FPTU.repository.CustomerRepository;
import com.FPTU.service.CommentService;
import com.FPTU.service.CourseDiscountService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        Comment comment = new Comment();
        if (commentDTO.getId() != null) {
            Comment oldComment = commentRepository.getOne(commentDTO.getId());
            comment = commentConverter.toEntity(commentDTO, oldComment);
        } else {
            comment = commentConverter.toEntity(commentDTO);
        }
        Course course = courseRepository.getOne(commentDTO.getCourseId());
        Customer customer = customerRepository.getOne(commentDTO.getCustomerId());
        comment.setCourse(course);
        comment.setCustomer(customer);
        comment = commentRepository.save(comment);
        return commentConverter.toDTO(comment);
    }


}