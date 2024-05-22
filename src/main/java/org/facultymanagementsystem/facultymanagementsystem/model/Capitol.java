package org.facultymanagementsystem.facultymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Capitol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nume;
    private String descriere;
    @OneToMany(mappedBy = "capitol")
    private List<File> fisiere;
    private boolean vizibil;
    @ManyToOne
    @JoinColumn(name = "curs_id")
    private Curs curs;
    @ManyToMany
    @JoinTable(
            name = "capitol_user_fisier",
            joinColumns = @JoinColumn(name = "capitol_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> utilizatori;

    public Capitol(String nume, String descriere, boolean vizibil) {
        this.nume = nume;
        this.descriere = descriere;
        this.vizibil = vizibil;
    }
}
