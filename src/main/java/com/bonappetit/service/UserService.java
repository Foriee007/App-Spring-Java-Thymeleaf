package com.bonappetit.service;

import com.bonappetit.model.dto.RegisterDTO;
import com.bonappetit.model.dto.UserDTO;
import com.bonappetit.model.entity.User;

import java.util.Optional;

public interface UserService {

    void login(String username) ;
    boolean checkCredentials(String username, String password);

    boolean register(RegisterDTO registerDTO);

    void logout();

    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    Optional<User> findById(Long id);
}
