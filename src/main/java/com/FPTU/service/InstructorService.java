package com.FPTU.service;

import com.FPTU.model.Instructor;
import com.FPTU.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    public Instructor addInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

}
