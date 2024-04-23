package org.facultymanagementsystem.facultymanagementsystem.service;

import lombok.AllArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.Profesor;
import org.facultymanagementsystem.facultymanagementsystem.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProfesorServiceImpl implements PreofesorService{
    private final ProfesorRepository profesorRepository;
    @Override
    public List<Profesor> getAllProfesor() {
        return profesorRepository.findAll();
    }

    @Override
    public void save(Profesor profesor) {
        profesorRepository.save(profesor);
    }

    @Override
    public Profesor getById(Integer id) {
        Optional<Profesor> profesors = profesorRepository.findById(id);
        Profesor profesor = null;
        if (profesors.isPresent())
            profesor = profesors.get();
        else
            throw new RuntimeException(
                    "Student is not found for id : " + id);
        return profesor;
    }

    @Override
    public void deleteById(Integer id) {
        profesorRepository.deleteById(id);
    }
}
