package org.facultymanagementsystem.facultymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.Curs;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.repository.CursRepository;
import org.facultymanagementsystem.facultymanagementsystem.service.CursService;
import org.facultymanagementsystem.facultymanagementsystem.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final CursService cursService;
    private final UserService userService;

    @GetMapping("/enroll")
    public String pageAddToCourse(Model model) {
        return "add-to-course";
    }

    @PostMapping("/checkCourseCode")
    public String checkCourseCode(@RequestParam("courseCode") String courseCode, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        boolean success = cursService.addUserToCurs(courseCode, userEmail);

        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Course registration successful!");
        } else {
            redirectAttributes.addFlashAttribute("failMessage", "Course registration failed. Invalid course code or user.");
        }

        return "redirect:/";
    }
}