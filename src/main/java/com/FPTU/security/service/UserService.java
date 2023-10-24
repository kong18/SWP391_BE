package com.FPTU.security.service;

import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.model.User;
import com.FPTU.security.dto.RegistrationRequest;
import com.FPTU.security.dto.RegistrationResponse;


public interface UserService {

  User findByUsername(String username);

  RegistrationResponse register(RegistrationRequest registrationRequest);

  AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

  void updateUser(User user);
}
