package com.FPTU.controller;


import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.model.User;
import com.FPTU.security.dto.UserResponse;
import com.FPTU.security.mapper.UserMapper;
import com.FPTU.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper = UserMapper.INSTANCE;

  @GetMapping(path = "/{username}")
  public ResponseEntity<UserResponse> findUserByUsername(@PathVariable String username) {
    final User user = userService.findByUsername(username);
    final AuthenticatedUserDto userDto = userMapper.convertToAuthenticatedUserDto(user);
    return ResponseEntity.ok(new UserResponse(userDto));
  }

}
