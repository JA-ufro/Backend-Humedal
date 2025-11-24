package com.buenavista.humedal.dto;

import lombok.Data;

import java.util.List;

@Data
public class HumedalUpdateDTO {

    private String nombre;
    private String subtitulo;
    private String descripcionCorta;
    private String descripcionLarga;
    private String ubicacion;
    private String mapaUrl;
    private String imagenPrincipal;
    private List<String> galeria;
}
