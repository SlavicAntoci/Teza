package org.facultymanagementsystem.facultymanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.Capitol;
import org.facultymanagementsystem.facultymanagementsystem.model.Curs;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.service.CapitolService;
import org.facultymanagementsystem.facultymanagementsystem.service.CursService;
import org.facultymanagementsystem.facultymanagementsystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/theacher")
public class ProfesorController {
    private final UserService userService;
    private final CursService cursService;
    private final CapitolService capitolService;

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
    @GetMapping("/capitole/nou")
    public String afisareFormularAdaugareCapitol(Model model) {
        model.addAttribute("capitol", new Capitol());
        return "formularAdaugareCapitol";
    }

    @PostMapping("/capitole/nou")
    public String adaugareCapitol(@ModelAttribute("capitol") Capitol capitol) {
        capitolService.adaugareCapitol(capitol);
        return "redirect:/profesor";
    }

    @GetMapping("/capitole/{id}/editare")
    public String afisareFormularEditareCapitol(@PathVariable("id") Long id, Model model) {
        model.addAttribute("capitol", capitolService.getCapitolById(id));
        return "formularEditareCapitol";
    }

    @PostMapping("/capitole/{id}/editare")
    public String editareCapitol(@ModelAttribute("capitol") Capitol capitol) {
        capitolService.editareCapitol(capitol);
        return "redirect:/profesor";
    }

    @GetMapping("/capitole/{id}/stergere")
    public String stergereCapitol(@PathVariable("id") Long id) {
        capitolService.stergereCapitol(id);
        return "redirect:/profesor";
    }
    @GetMapping("/courseDetails")
    public String afisareDetaliiCurs(@RequestParam("courseId") Long courseId, Model model) {
        Curs curs = cursService.getCursById(courseId);

        if (curs == null) {
            return "redirect:/eroare";
        }

        List<Capitol> capitole = capitolService.getAllCapitoleForCurs(courseId);

        // Adaugă cursul și capitolele în model pentru afișare în pagina HTML
        model.addAttribute("curs", curs);
        model.addAttribute("capitole", capitole);

        return "detaliiCursProfesor";
    }

}
