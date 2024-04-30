package org.facultymanagementsystem.facultymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Curs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nume;
    @Column(unique = true)
    private String parola;
    @OneToOne
    @JoinColumn(name = "email_profesor")
    private User profesor;
    @ManyToMany(mappedBy = "cursuri")
    private Collection<User> users;

    public Curs(String nume, User profesor) {
        this.nume = nume;
        this.profesor = profesor;
    }
}
