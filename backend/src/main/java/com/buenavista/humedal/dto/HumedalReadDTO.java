package com.buenavista.humedal.dto;

import java.util.List;
import lombok.Data;

@Data
public class HumedalReadDTO {

    private Long id;
    private String nombre;
    private String subtitulo;
    private String descripcionCorta;
    private String descripcionLarga;
    private String ubicacion;
    private String mapaUrl;
    private String imagenPrincipal;

    private List<String> galeria;

    private List<EspecieResumenDTO> especies;
    private List<TestimonioReadDTO> testimonios;
}

