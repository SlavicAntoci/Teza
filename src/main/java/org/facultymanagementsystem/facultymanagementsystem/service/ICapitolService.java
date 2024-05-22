package org.facultymanagementsystem.facultymanagementsystem.service;

import org.facultymanagementsystem.facultymanagementsystem.model.Capitol;

import java.util.List;

public interface ICapitolService {
    List<Capitol> getAllCapitole();

    List<Capitol> getAllCapitoleForCurs(Long cursId);
    void adaugareCapitol(Capitol capitol);
    void editareCapitol(Capitol capitol);
    void stergereCapitol(Long id);
    Capitol getCapitolById(Long id);

}
