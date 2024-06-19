package com.bonappetit.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class RegisterDTO {

    @Size(min = 3, max = 20)
    @NotNull
    private String username;

    @Email(message = "Enter valid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @Size(min = 3, max = 20)
    @NotNull
    private String password;

    @NotNull
    @Size(min = 3, max = 20)
    private String confirmPassword;

    public RegisterDTO() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
