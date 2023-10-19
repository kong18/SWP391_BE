package com.FPTU.service.impl;

import com.FPTU.converter.CourseConverter;
import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseCategory;
import com.FPTU.model.User;
import com.FPTU.repository.*;
import com.FPTU.service.CommentService;
import com.FPTU.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseConverter courseConverter;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CourseDetailRepository courseDetailRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseConverter::toDTO)
                .peek(cDTO -> {
                    cDTO.setRating(ratingRepository.findAverageRating(cDTO.getId()));
                    if(cDTO.getRating() == null) {
                        cDTO.setRating(0.0);
                    }
                    cDTO.setDuration(courseDetailRepository.getSumEstimatedTimeByCourseId(cDTO.getId()));
                    if(cDTO.getDuration() == null) {
                        cDTO.setDuration(0L);
                    }
                    cDTO.setComments(commentService.getCommentsByCourseId(cDTO.getId()));
                })
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.getId() != null) {
            Course oldCourse = courseRepository.getOne(courseDTO.getId());
            course = courseConverter.toEntity(courseDTO, oldCourse);
        } else {
            course = courseConverter.toEntity(courseDTO);
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDateTime = now.format(formatter);
            course.setCreatedDate(formattedDateTime);
        }
        CourseCategory courseCategory = courseCategoryRepository.getOne(courseDTO.getCategory().getId());
        User user = userRepository.getOne(courseDTO.getUserId());
        course.setCourseCategory(courseCategory);
        course.setUser(user);

        course = courseRepository.save(course);
        return courseConverter.toDTO(course);
    }

    @Override
    public List<CourseDTO> findAllByCategoryId(Long categoryId) {
        List<Course> courses = courseRepository.findByCourseCategory_CategoryId(categoryId);
        return courses.stream()
                .map(courseConverter::toDTO)
                .peek(cDTO -> {
                    cDTO.setRating(ratingRepository.findAverageRating(cDTO.getId()));
                    if(cDTO.getRating() == null) {
                        cDTO.setRating(0.0);
                    }
                    cDTO.setDuration(courseDetailRepository.getSumEstimatedTimeByCourseId(cDTO.getId()));
                    if(cDTO.getDuration() == null) {
                        cDTO.setDuration(0L);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO findById(Long id) {
        Course c = courseRepository.getOne(id);
        CourseDTO cDTO = courseConverter.toDTO(c);
        return cDTO;
    }

    @Override
    public List<CourseDTO> findByName(String title) {
        List<Course> courses = courseRepository.findByNameContainingIgnoreCase(title);
        return courses.stream()
                .map(courseConverter::toDTO)
                .peek(cDTO -> {
                    cDTO.setRating(ratingRepository.findAverageRating(cDTO.getId()));
                    if(cDTO.getRating() == null) {
                        cDTO.setRating(0.0);
                    }
                    cDTO.setDuration(courseDetailRepository.getSumEstimatedTimeByCourseId(cDTO.getId()));
                    if(cDTO.getDuration() == null) {
                        cDTO.setDuration(0L);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> findAllByUserId_RoleCustomer(Long id) {
        List<Course> courses = courseRepository.findAllByUserIdRoleCustomer(id);
        return courses.stream()
                .map(courseConverter::toDTO)
                .peek(cDTO -> {
                    cDTO.setRating(ratingRepository.findAverageRating(cDTO.getId()));
                    if(cDTO.getRating() == null) {
                        cDTO.setRating(0.0);
                    }
                    cDTO.setDuration(courseDetailRepository.getSumEstimatedTimeByCourseId(cDTO.getId()));
                    if(cDTO.getDuration() == null) {
                        cDTO.setDuration(0L);
                    }
                })
                .collect(Collectors.toList());
    }


}
