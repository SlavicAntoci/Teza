package org.facultymanagementsystem.facultymanagementsystem.service;

import org.facultymanagementsystem.facultymanagementsystem.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
    void save(Student student);
    Student getById(Integer id);
    void deleteById(Integer id);
}
