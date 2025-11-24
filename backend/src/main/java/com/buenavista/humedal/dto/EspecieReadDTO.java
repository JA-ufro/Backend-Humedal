package com.buenavista.humedal.dto;

import com.buenavista.humedal.model.EstadoConservacion;
import com.buenavista.humedal.model.TipoEspecie;
import lombok.Data;

@Data
public class EspecieReadDTO {

    private Long id;

    private String nombreComun;
    private String nombreCientifico;
    private String descripcion;

    private TipoEspecie tipo;
    private EstadoConservacion estadoConservacion;

    private String imagenUrl;

    private Long humedalId;
}

