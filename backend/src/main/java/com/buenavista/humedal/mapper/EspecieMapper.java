package com.buenavista.humedal.mapper;

import com.buenavista.humedal.dto.*;
import com.buenavista.humedal.model.Especie;

public class EspecieMapper {

    public static EspecieReadDTO toReadDTO(Especie entity) {
        EspecieReadDTO dto = new EspecieReadDTO();

        dto.setId(entity.getId());
        dto.setNombreComun(entity.getNombreComun());
        dto.setNombreCientifico(entity.getNombreCientifico());
        dto.setDescripcion(entity.getDescripcion());
        dto.setTipo(entity.getTipo());
        dto.setEstadoConservacion(entity.getEstadoConservacion());
        dto.setImagenUrl(entity.getImagenUrl());

        if (entity.getHumedal() != null)
            dto.setHumedalId(entity.getHumedal().getId());

        return dto;
    }

    public static EspecieResumenDTO toResumenDTO(Especie entity) {
        EspecieResumenDTO dto = new EspecieResumenDTO();
        dto.setId(entity.getId());
        dto.setNombreComun(entity.getNombreComun());
        dto.setImagenUrl(entity.getImagenUrl());
        return dto;
    }

    public static Especie toEntity(EspecieCreateDTO dto) {
        Especie e = new Especie();
        e.setNombreComun(dto.getNombreComun());
        e.setNombreCientifico(dto.getNombreCientifico());
        e.setDescripcion(dto.getDescripcion());
        e.setTipo(dto.getTipo());
        e.setEstadoConservacion(dto.getEstadoConservacion());
        e.setImagenUrl(dto.getImagenUrl());
        return e;
    }

    public static void updateEntity(Especie e, EspecieUpdateDTO dto) {
        if (dto.getNombreComun() != null) e.setNombreComun(dto.getNombreComun());
        if (dto.getNombreCientifico() != null) e.setNombreCientifico(dto.getNombreCientifico());
        if (dto.getDescripcion() != null) e.setDescripcion(dto.getDescripcion());
        if (dto.getTipo() != null) e.setTipo(dto.getTipo());
        if (dto.getEstadoConservacion() != null) e.setEstadoConservacion(dto.getEstadoConservacion());
        if (dto.getImagenUrl() != null) e.setImagenUrl(dto.getImagenUrl());
    }
}


