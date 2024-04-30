package org.facultymanagementsystem.facultymanagementsystem.repository;

import org.facultymanagementsystem.facultymanagementsystem.model.Curs;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursRepository extends JpaRepository<Curs, Long> {
    Optional<Curs> findByParola(String parola);
    boolean existsByParola(String parola);
    List<Curs> findByProfesor(User profesor);
    List<Curs> findAllByUsersContaining(User user);
}
