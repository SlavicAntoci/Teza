package org.facultymanagementsystem.facultymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    @GetMapping()
    public String homePage(Model model){
        return "index";
    }
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/error")
    public String error(Model model){
        return "error";
    }
}
