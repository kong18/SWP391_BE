package com.FPTU.controller;


import com.FPTU.dto.RatingDTO;
import com.FPTU.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@CrossOrigin("http://127.0.0.1:5173/")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping()
    public ResponseEntity<String> addRating(@RequestBody RatingDTO ratingDTO) {
        ratingService.save(ratingDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Rating added successfully");
    }

}