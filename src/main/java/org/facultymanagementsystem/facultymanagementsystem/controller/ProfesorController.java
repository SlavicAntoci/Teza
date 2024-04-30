package org.facultymanagementsystem.facultymanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.Curs;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.service.CursService;
import org.facultymanagementsystem.facultymanagementsystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/theacher")
public class ProfesorController {
    private final UserService userService;
    private final CursService cursService;

    @GetMapping("/page")
    public String pageTeacher(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String professorEmail = authentication.getName();
        User professor = userService.findByEmail(professorEmail).orElseThrow(); // Assuming the email is always present
        List<Curs> courses = cursService.getCoursesForProfessor(professor);
        model.addAttribute("courses", courses);
        return "theacher";
    }

    @GetMapping("/addCourse")
    public String addCourse() {
        return "cour-add-formular";
    }

    @PostMapping("/addCourse")
    public String addCourse(@RequestParam("nume") String nume) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String profesorEmail = authentication.getName();

        User profesor = userService.findByEmail(profesorEmail).orElseThrow();

        Curs curs = new Curs(nume, profesor);
        cursService.addCurs(curs);
        return "redirect:/theacher/addCourse?succes_addcourse";
    }
}
