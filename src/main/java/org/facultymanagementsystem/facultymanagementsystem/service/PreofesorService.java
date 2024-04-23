package org.facultymanagementsystem.facultymanagementsystem.service;

import org.facultymanagementsystem.facultymanagementsystem.model.Profesor;
import org.facultymanagementsystem.facultymanagementsystem.model.Student;

import java.util.List;

public interface PreofesorService {
    List<Profesor> getAllProfesor();
    void save(Profesor profesor);
    Profesor getById(Integer id);
    void deleteById(Integer id);
}
