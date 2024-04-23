package org.facultymanagementsystem.facultymanagementsystem.repository;

import org.facultymanagementsystem.facultymanagementsystem.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer>{
}
