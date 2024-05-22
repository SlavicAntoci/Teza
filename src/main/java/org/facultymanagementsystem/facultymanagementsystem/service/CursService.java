package org.facultymanagementsystem.facultymanagementsystem.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.Curs;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.repository.CursRepository;
import org.facultymanagementsystem.facultymanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursService implements ICursService{
    private final CursRepository cursRepository;
    private final UserRepository userRepository;
    @Override
    public List<Curs> getAllCurs() {
        return cursRepository.findAll();
    }

    @Override
    public Curs findById(Long id) {
        return cursRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Not found curs"));
    }

    @Override
    public void addCurs(Curs curs) {
        curs.setParola(generateUniqueParola());
        cursRepository.save(curs);
    }

    public Boolean addUserToCurs(String courseCode, String userEmail) {
        Curs curs = cursRepository.findByParola(courseCode)
                .orElse(null);

        User user = userRepository.findByEmail(userEmail)
                .orElse(null);

        if (curs != null && user != null) {
            user.getCursuri().add(curs);
            curs.getUsers().add(user);
            userRepository.save(user);
            cursRepository.save(curs);
            return true;
        }

        return false;
    }


    public List<Curs> getCoursesForProfessor(User professor) {
        return cursRepository.findByProfesor(professor);
    }

    public Curs findByParola(String parola){
        return cursRepository.findByParola(parola).orElseThrow(() -> new EntityNotFoundException("Curs not found with id: " + parola));
    }

    private String generateUniqueParola() {
        String uniqueParola;
        do {
            uniqueParola = UUID.randomUUID().toString();
        } while (!isParolaUnique(uniqueParola));
        return uniqueParola;
    }
    private boolean isParolaUnique(String parola) {
         return !cursRepository.existsByParola(parola);
    }


    public List<Curs> getCoursesForUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return Collections.emptyList();
        }

        return cursRepository.findAllByUsersContaining(user);
    }

    @Override
    public Curs getCursById(Long idCurs) {
        return cursRepository.findById(idCurs).orElseThrow(()->new EntityNotFoundException("Nu sa gasit curs"));
    }
}
