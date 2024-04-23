package org.facultymanagementsystem.facultymanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nume;
    private String prenume;
//    private Date anNastere;
//    private Integer anulAdmiterii;
//    private Integer anStudii;
//    private Sex sex;
//    private Integer idFacultate;

    public Student(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
    }
}
