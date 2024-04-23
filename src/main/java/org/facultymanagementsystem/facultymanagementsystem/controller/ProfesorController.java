package org.facultymanagementsystem.facultymanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.Profesor;
import org.facultymanagementsystem.facultymanagementsystem.service.ProfesorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class ProfesorController {
    private final ProfesorServiceImpl profesorService;
    @GetMapping("/profesor")
    public String viewProfesor(Model model){
        model.addAttribute("listprof", profesorService.getAllProfesor());
        return "profesor";
    }
    @GetMapping("/addprof")
    public String addprof(Model model){
        Profesor profesor = new Profesor();
        model.addAttribute("profesor", profesor);
        return "newprof";
    }
    @PostMapping("/profesor/save")
    public String saveprof(@ModelAttribute("profesor") Profesor profesor){
        profesorService.save(profesor);
        return "redirect:/profesor";
    }
    @GetMapping("/profesor/delelte/{id}")
    public String deleteprofe(@PathVariable(value = "id") Integer id){
        profesorService.deleteById(id);
        return "redirect:/profesor";
    }
    @GetMapping("/update/prof/{id}")
    public String updateprof(@PathVariable(value = "id") Integer id,Model model){
        Profesor profesor = profesorService.getById(id);
        model.addAttribute("prof", profesor);
        return "updateprof";
    }
}
