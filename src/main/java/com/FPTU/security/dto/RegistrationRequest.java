package com.FPTU.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.FPTU.model.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegistrationRequest extends Request {

  @NotEmpty(message = "{registration_name_not_empty}")
  private String name;

  @Email(message = "{registration_email_is_not_valid}")
  @NotEmpty(message = "{registration_email_not_empty}")
  private String email;

  @NotEmpty(message = "{registration_username_not_empty}")
  private String username;

  @NotEmpty(message = "{registration_userRole_not_empty}")
  private String role;

  @NotEmpty(message = "{registration_password_not_empty}")
  private String password;

}
