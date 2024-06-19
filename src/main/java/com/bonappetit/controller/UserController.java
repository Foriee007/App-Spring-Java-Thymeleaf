package com.bonappetit.controller;

import com.bonappetit.model.dto.LoginDTO;
import com.bonappetit.model.dto.RegisterDTO;
import com.bonappetit.service.UserService;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final LoggedUser loggedUser;
    private final UserService userService;

    public UserController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @ModelAttribute("loginData")
    public LoginDTO loginData() {
        return new LoginDTO();
    }

    @GetMapping("/login")
    public String login(Model model){
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "login";
    }


    @PostMapping("/login")
    public String loginConfirm(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDta", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);
            return "redirect:/users/login";
        }
        boolean validCredentials = this.userService.checkCredentials(loginDTO.getUsername(), loginDTO.getPassword());

        if (!validCredentials) {
            redirectAttributes.addFlashAttribute("loginDta", loginDTO);
            redirectAttributes.addFlashAttribute("validCredentials", false);
            return "redirect:/users/login";
        }
        this.userService.login(loginDTO.getUsername());
        return "redirect:/home";
    }
//Register
    @ModelAttribute("registerData")
    public RegisterDTO registerData() {
        return new RegisterDTO();
}
    @GetMapping("/register")
    public String register(){
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid RegisterDTO registerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors() || !registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerData", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/users/register";
        }
        boolean success = userService.register(registerDTO);
        if (!success) {
            return "redirect:/users/register";
        }
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout(){
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        this.loggedUser.logout();
        return "redirect:/";
    }

    @ModelAttribute()
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }
}
