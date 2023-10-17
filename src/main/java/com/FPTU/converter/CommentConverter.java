package com.FPTU.converter;

import com.FPTU.dto.CommentDTO;
import com.FPTU.dto.CourseDiscountDTO;
import com.FPTU.dto.RatingDTO;
import com.FPTU.model.Comment;
import com.FPTU.model.CourseDiscount;
import com.FPTU.model.Rating;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    public Comment toEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        return comment;
    }
    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getCommentId());
        commentDTO.setCourseId(comment.getCourse().getCourseId());
        commentDTO.setCustomerId(comment.getCustomer().getCustomerId());
        commentDTO.setComment(comment.getComment());
        return commentDTO;
    }

    public Comment toEntity(CommentDTO commentDTO, Comment comment) {
        comment.setComment(commentDTO.getComment());
        return comment;
    }

}