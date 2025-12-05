package com.buenavista.humedal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TestimonioReadDTO {

    private Long id;
    private String autor;
    private String contenido;
    private LocalDate fecha;
    private Long humedalId;

    public TestimonioReadDTO(Long id, String autor, String contenido, LocalDate fecha, Long humedalId) {
        this.id = id;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
        this.humedalId = humedalId;
    }

    public TestimonioReadDTO() {

    }
}

