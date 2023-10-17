package com.FPTU.service.impl;

import com.FPTU.converter.CourseDiscountConverter;
import com.FPTU.converter.RatingConverter;
import com.FPTU.dto.CourseDiscountDTO;
import com.FPTU.dto.RatingDTO;
import com.FPTU.model.*;
import com.FPTU.repository.CourseDiscountRepository;
import com.FPTU.repository.CourseRepository;
import com.FPTU.repository.CustomerRepository;
import com.FPTU.repository.RatingRepository;
import com.FPTU.service.CourseDiscountService;
import com.FPTU.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private RatingConverter ratingConverter;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public RatingDTO save(RatingDTO ratingDTO) {
        Rating rating = new Rating();
        if (ratingDTO.getId() != null) {
            Rating oldRating = ratingRepository.getOne(ratingDTO.getId());
            rating = ratingConverter.toEntity(ratingDTO, oldRating);
        } else {
            rating = ratingConverter.toEntity(ratingDTO);
        }
        Course course = courseRepository.getOne(ratingDTO.getCourseId());
        Customer customer = customerRepository.getOne(ratingDTO.getCustomerId());
        rating.setCourse(course);
        rating.setCustomer(customer);
        rating = ratingRepository.save(rating);
        return ratingConverter.toDTO(rating);
    }


}