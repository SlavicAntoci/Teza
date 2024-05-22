package org.facultymanagementsystem.facultymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeFisier;
    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "capitol_id")
    private Capitol capitol;

    public File(String fileName, byte[] data) {
        this.numeFisier = fileName;
        this.data = data;
    }}
