package com.FPTU.converter;
import com.FPTU.dto.CourseDetailDTO;
import com.FPTU.dto.RatingDTO;
import com.FPTU.model.CourseDetail;
import com.FPTU.model.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingConverter {
    public Rating toEntity(RatingDTO ratingDTO) {
        Rating rating = new Rating();
        rating.setRating(ratingDTO.getRating());
        return rating;
    }
    public RatingDTO toDTO(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getRatingId());
        ratingDTO.setCourseId(rating.getCourse().getCourseId());
        ratingDTO.setCustomerId(rating.getCustomer().getCustomerId());
        ratingDTO.setRating(rating.getRating());
        return ratingDTO;
    }

    public Rating toEntity(RatingDTO ratingDTO, Rating rating) {
        rating.setRating(ratingDTO.getRating());
        return rating;
    }

}