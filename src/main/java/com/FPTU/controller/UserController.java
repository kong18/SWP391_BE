package com.FPTU.controller;

import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.model.User;
import com.FPTU.repository.UserRepository;
import com.FPTU.security.dto.UserResponse;
import com.FPTU.security.mapper.UserMapper;
import com.FPTU.security.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserRepository repo;
  private final UserService userService;
  private final UserMapper userMapper = UserMapper.INSTANCE;
  private final Cloudinary cloudinary;  // Inject the Cloudinary bean


  @GetMapping(path = "/{username}")
  public ResponseEntity<UserResponse> findUserByUsername(@PathVariable String username) {
    final User user = userService.findByUsername(username);
    final AuthenticatedUserDto userDto = userMapper.convertToAuthenticatedUserDto(user);
    return ResponseEntity.ok(new UserResponse(userDto));
  }

  @PostMapping("/upload/image/{username}")
  public ResponseEntity<?> uploadImage(@PathVariable("username") String username,
                                       @RequestParam("image") MultipartFile multipartFile) throws IOException {
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

    // Save the file to Cloudinary and get the upload result
    Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.asMap("public_id", fileName));

    // Check if the upload was successful and retrieve the public URL
    if (uploadResult != null && uploadResult.containsKey("secure_url")) {
      String imageUrl = uploadResult.get("secure_url").toString();

      User user = repo.findByUsername(username);
      // Update the user's image URL
      user.setImg(imageUrl);

      // Save the updated user information
      repo.save(user);



      return ResponseEntity.status(HttpStatus.CREATED).body("Upload successful");
    } else {
      // Handle the case where the file upload to Cloudinary failed
      return new ResponseEntity<>("Failed to upload the image", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}