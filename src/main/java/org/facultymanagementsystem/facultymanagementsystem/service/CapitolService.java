package org.facultymanagementsystem.facultymanagementsystem.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.Capitol;
import org.facultymanagementsystem.facultymanagementsystem.repository.CapitolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CapitolService implements ICapitolService{
    private final CapitolRepository capitolRepository;
    @Override
    public List<Capitol> getAllCapitole() {
        return capitolRepository.findAll();
    }

    @Override
    public List<Capitol> getAllCapitoleForCurs(Long cursId) {
        return capitolRepository.findByCursId(cursId);
    }
    public void adaugareCapitol(Capitol capitol) {
        capitolRepository.save(capitol);
    }

    public void editareCapitol(Capitol capitol) {
        capitolRepository.save(capitol);
    }

    public void stergereCapitol(Long id) {
        capitolRepository.deleteById(id);
    }

    public Capitol getCapitolById(Long id) {
        Optional<Capitol> capitol = capitolRepository.findById(id);
        return capitol.orElseThrow(()->new EntityNotFoundException("Nu a fost gasit Capitolul"));
    }


}
