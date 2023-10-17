package com.FPTU.controller;

import com.FPTU.dto.CommentDTO;
import com.FPTU.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@CrossOrigin("http://127.0.0.1:5173/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping()
    public ResponseEntity<CommentDTO> addCourse(@RequestBody CommentDTO commentDTO) {
        commentDTO = commentService.save(commentDTO);
        return  ResponseEntity.ok(commentDTO);
    }

}