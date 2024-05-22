package org.facultymanagementsystem.facultymanagementsystem.repository;

import org.facultymanagementsystem.facultymanagementsystem.model.Capitol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapitolRepository extends JpaRepository<Capitol,Long> {
    List<Capitol> findByCursId(Long cursId);

}
