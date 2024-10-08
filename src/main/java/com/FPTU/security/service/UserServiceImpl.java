package com.FPTU.security.service;

import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.exceptions.UserNotFoundException;
import com.FPTU.model.User;
import com.FPTU.model.UserRole;
import com.FPTU.repository.UserRepository;
import com.FPTU.security.dto.RegistrationRequest;
import com.FPTU.security.dto.RegistrationResponse;
import com.FPTU.security.mapper.UserMapper;
import com.FPTU.service.UserValidationService;
import com.FPTU.utils.ExceptionMessageAccessor;
import com.FPTU.utils.GeneralMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String REGISTRATION_SUCCESSFUL = "registration_successful";
  private static final String USERNAME_NOT_FOUND = "username_not_found";

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder passwordEncoder;

  private final UserValidationService userValidationService;

  private final GeneralMessageAccessor generalMessageAccessor;

  private final ExceptionMessageAccessor exceptionMessageAccessor;

  private final UserMapper userMapper = UserMapper.INSTANCE;

  @Override
  public User findByUsername(String username) {
    final User user = userRepository.findByUsername(username);
    if (user == null) {
      final String errorMessage = exceptionMessageAccessor.getMessage(
              null, USERNAME_NOT_FOUND);
      throw new UserNotFoundException(errorMessage);
    } else {
      return user;
    }
  }

  @Override
  public RegistrationResponse register(RegistrationRequest registrationRequest) {

    userValidationService.validateUser(registrationRequest);

    final User user = userMapper.convertToUser(registrationRequest);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setUserRole(UserRole.valueOf(registrationRequest.getRole()));

    userRepository.save(user);

    final String username = registrationRequest.getUsername();
    final String registrationSuccessMessage = generalMessageAccessor.getMessage(
            registrationRequest.getLocale(), REGISTRATION_SUCCESSFUL, username);

    log.info("{} registered successfully!", username);

    return new RegistrationResponse(registrationSuccessMessage);
  }

  @Override
  public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

    final User user = findByUsername(username);

    return userMapper.convertToAuthenticatedUserDto(user);
  }
}