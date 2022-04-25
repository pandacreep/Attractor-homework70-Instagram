package com.pandacreep.instagram.model.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDtoRegister {
    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a well-formed email address")
    private String email = "";

    @Size(min=4, max=24, message = "name length must be >= 4 and <= 24")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "name should contain only letters")
    private String name = "";

    @Size(min=3, max=10, message = "password length must be >= 3 and <= 10")
    private String password = "";

    private MultipartFile avatar;
    private String description;
    private String phoneNumber;
    private String gender;
}