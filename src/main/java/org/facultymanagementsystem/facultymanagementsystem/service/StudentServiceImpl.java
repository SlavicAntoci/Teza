package org.facultymanagementsystem.facultymanagementsystem.service;

import lombok.AllArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.Student;
import org.facultymanagementsystem.facultymanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getById(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;
        if (optional.isPresent())
            student = optional.get();
        else
            throw new RuntimeException(
                    "Student is not found for id : " + id);
        return student;
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
}
