package com.FPTU.controller;


import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.model.User;
import com.FPTU.repository.UserRepository;
import com.FPTU.security.dto.UserResponse;
import com.FPTU.security.mapper.UserMapper;
import com.FPTU.security.service.UserService;
import com.FPTU.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  @Autowired
  private UserRepository repo;
  private final UserService userService;
  private final UserMapper userMapper = UserMapper.INSTANCE;

  @GetMapping(path = "/{username}")
  public ResponseEntity<UserResponse> findUserByUsername(@PathVariable String username) {
    final User user = userService.findByUsername(username);
    final AuthenticatedUserDto userDto = userMapper.convertToAuthenticatedUserDto(user);
    return ResponseEntity.ok(new UserResponse(userDto));
  }

  @PostMapping("/save")
  public RedirectView saveUser(User user,
                               @RequestParam("image") MultipartFile multipartFile) throws IOException {

    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    user.setImg(fileName);

    User savedUser = repo.save(user);

    String uploadDir = "user-photos/" + savedUser.getUserId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

    return new RedirectView("/users", true);
  }

}
