package com.buenavista.humedal.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "humedal")
public class Humedal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String subtitulo;

    @Column(length = 500)
    private String descripcionCorta;

    @Column(length = 2000)
    private String descripcionLarga;

    private String ubicacion;

    @Column(length = 1000)
    private String mapaUrl;

    private String imagenPrincipal; // Guardaremos el nombre del archivo, ej: "humedal-carmen.jpg"

    @OneToMany(mappedBy = "humedal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Especie> especie;

    @OneToMany(mappedBy = "humedal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Testimonio> testimonios;

    @ElementCollection
    @CollectionTable(name = "humedal_galeria", joinColumns = @JoinColumn(name = "humedal_id"))
    @Column(name = "imagen_url")
    private List<String> galeria; // Nombres de los archivos de imagen
}