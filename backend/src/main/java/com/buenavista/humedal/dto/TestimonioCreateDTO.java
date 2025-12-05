package com.buenavista.humedal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TestimonioCreateDTO {

    private String autor;
    private String contenido;
    private Long humedalId;
    private LocalDate fecha;
}
