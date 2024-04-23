package org.facultymanagementsystem.facultymanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.Student;
import org.facultymanagementsystem.facultymanagementsystem.service.StudentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class StudentController {
    private final StudentServiceImpl service;

    @GetMapping("/student")
    public String viewHomePage(Model model) {
        model.addAttribute("allemplist", service.getAllStudent());
        return "student";
    }
    @GetMapping("/addnew")
    public String addNewStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "newstudent";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.save(student);
        return "redirect:/student";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        Student student = service.getById(id);
        model.addAttribute("student", student);
        return "update";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteThroughId(@PathVariable(value = "id") Integer id) {
        service.deleteById(id);
        return "redirect:/student";

    }

}
