package com.bonappetit.service;

import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.dto.RegisterDTO;
import com.bonappetit.model.dto.UserDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    void login(String username);

    boolean checkCredentials(String username, String password);

    boolean register(RegisterDTO registerDTO);

    void logout();
    //Set<RecipeInfoDTO> findFavourites(Long userId); //OLD Method save it for reference

    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    Optional<User> findById(Long id);

    Collection<Recipe> getFavorite(long userId);
}
