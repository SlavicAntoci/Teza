package org.facultymanagementsystem.facultymanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nume;
    private String prenume;
    //private String idGradStiintific;

    public Profesor(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
    }
}
