package com.bonappetit.service.impl;

import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.dto.RegisterDTO;
import com.bonappetit.model.dto.UserDTO;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.UserService;
import com.bonappetit.util.LoggedUser;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(PasswordEncoder encoder, LoggedUser loggedUser, UserRepository userRepository, ModelMapper modelMapper) {
        this.encoder = encoder;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void login(String username) { //Register Logic use void method  to Controller
        User user = this.getUserByUsername(username);   //method User getUserByUsername
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }
    public User getUserByUsername(String username) { //check for Username
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public boolean checkCredentials(String username, String password) { //called from userController
        User user = this.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return encoder.matches(password, user.getPassword()); //Check for correct password
    }

    @Override
    public boolean register(RegisterDTO registerDTO) {//Register Logic return boolean to Controller
        Optional<User> existingUser = userRepository
                .findByUsernameOrEmail(registerDTO.getUsername(), registerDTO.getEmail());

        if (existingUser.isPresent()) {
            return false;
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));

        this.userRepository.save(user);
        return true;
    }

    @Override
    public void logout() {
        loggedUser.logout();
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }
        return this.mapUserDTO(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        User user = this.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        return this.mapUserDTO(user);
    }

    private UserDTO mapUserDTO(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }


    @Override
    @Transactional
    public Set<RecipeInfoDTO> findFavourites(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow()
                .getFavouriteRecipes()
                .stream().map(e -> modelMapper.map(e, RecipeInfoDTO.class))
                .collect(Collectors.toSet());
    }
}

