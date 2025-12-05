package com.buenavista.humedal.dto;

import lombok.Data;

@Data
public class EspecieResumenDTO {
    private Long id;
    private String nombreComun;
    private String imagenUrl;

    public EspecieResumenDTO(Long id, String nombreComun, String imagenUrl) {
        this.id = id;
        this.nombreComun = nombreComun;
        this.imagenUrl = imagenUrl;
    }

    public EspecieResumenDTO() {}
}

