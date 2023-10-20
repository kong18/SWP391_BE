package com.FPTU.repository;

import com.FPTU.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);

  List<User> findAll();

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

}
