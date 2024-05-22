package org.facultymanagementsystem.facultymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.facultymanagementsystem.facultymanagementsystem.model.Capitol;
import org.facultymanagementsystem.facultymanagementsystem.model.Curs;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.repository.UserRepository;
import org.facultymanagementsystem.facultymanagementsystem.service.CapitolService;
import org.facultymanagementsystem.facultymanagementsystem.service.CursService;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final CursService cursService;
    private final CapitolService capitolService;
    @GetMapping()
    public String homePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        List<Curs> userCourses = cursService.getCoursesForUser(userEmail);

        model.addAttribute("courses", userCourses);
        return "index";
    }
    @GetMapping("/courseDetails")
    public String getCourseDetails(@RequestParam("courseId") Long cursId, Model model) {
        Curs curs = cursService.findById(cursId);

        if (curs == null) {

            return "redirect:/error";
        }
        model.addAttribute("course", curs);
        List<Capitol> capitole = capitolService.getAllCapitoleForCurs(cursId);
        model.addAttribute("capitole", capitole);
        return "courseDetails";
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
