package com.FPTU.repository;

import com.FPTU.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);

  List<User> findAll();

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

}