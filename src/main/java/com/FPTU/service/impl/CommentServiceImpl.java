package com.FPTU.service.impl;

import com.FPTU.converter.CommentConverter;
import com.FPTU.converter.CourseDiscountConverter;
import com.FPTU.converter.CustomerConverter;
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
import java.util.stream.Collectors;


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
    @Autowired
    private CustomerConverter customerConverter;


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
        Customer customer = customerRepository.getOne(commentDTO.getCustomer().getId());
        comment.setCourse(course);
        comment.setCustomer(customer);
        comment = commentRepository.save(comment);
        return commentConverter.toDTO(comment);
    }

    public List<CommentDTO> getCommentsByCourseId(Long id) {
        List<Comment> comments = commentRepository.findByCourse_CourseId(id);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for ( Comment c: comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(c.getCommentId());
            commentDTO.setComment(c.getComment());
            commentDTO.setCustomer(customerConverter.toDTO(c.getCustomer()));
            commentDTOS.add(commentDTO);
        }
        return  commentDTOS;
    }


}
