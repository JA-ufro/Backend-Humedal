package com.buenavista.humedal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "especie")
public class Especie{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreComun;
    private String nombreCientifico;

    @Column(length = 1000)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TipoEspecie tipo;

    private String imagenUrl; // Nombre del archivo, ej: "tagua.jpg"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "humedal_id")
    private Humedal humedal;
}

