package com.buenavista.humedal.mapper;

import com.buenavista.humedal.dto.EspecieResumenDTO;
import com.buenavista.humedal.dto.HumedalCreateDTO;
import com.buenavista.humedal.dto.HumedalReadDTO;
import com.buenavista.humedal.dto.HumedalUpdateDTO;
import com.buenavista.humedal.dto.TestimonioReadDTO;
import com.buenavista.humedal.model.Humedal;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class HumedalMapper {

    public static HumedalReadDTO toReadDTO(Humedal entity) {
        if (entity == null) return null;

        HumedalReadDTO dto = new HumedalReadDTO();

        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setSubtitulo(entity.getSubtitulo());
        dto.setDescripcionCorta(entity.getDescripcionCorta());
        dto.setDescripcionLarga(entity.getDescripcionLarga());
        dto.setUbicacion(entity.getUbicacion());
        dto.setMapaUrl(entity.getMapaUrl());
        dto.setImagenPrincipal(entity.getImagenPrincipal());
        dto.setGaleria(entity.getGaleria());

        // Especies resumidas
        if (entity.getEspecies() != null) {
            dto.setEspecies(
                    entity.getEspecies().stream()
                            .map(especie -> new EspecieResumenDTO(
                                    especie.getId(),
                                    especie.getNombreComun(),
                                    especie.getImagenUrl()
                            ))
                            .collect(Collectors.toList())
            );
        }

        // Testimonios completos
        if (entity.getTestimonios() != null) {
            dto.setTestimonios(
                    entity.getTestimonios().stream()
                            .map(t -> new TestimonioReadDTO(
                                    t.getId(),
                                    t.getAutor(),
                                    t.getTexto(),
                                    t.getFecha(),
                                    t.getHumedal().getId()
                            ))
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static Humedal toEntity(HumedalCreateDTO dto) {
        Humedal h = new Humedal();

        h.setNombre(dto.getNombre());
        h.setSubtitulo(dto.getSubtitulo());
        h.setDescripcionCorta(dto.getDescripcionCorta());
        h.setDescripcionLarga(dto.getDescripcionLarga());
        h.setUbicacion(dto.getUbicacion());
        h.setMapaUrl(dto.getMapaUrl());
        h.setImagenPrincipal(dto.getImagenPrincipal());
        h.setGaleria(dto.getGaleria());

        return h;
    }

    public static void updateEntity(Humedal entity, HumedalUpdateDTO dto) {

        if (dto.getNombre() != null)
            entity.setNombre(dto.getNombre());

        if (dto.getSubtitulo() != null)
            entity.setSubtitulo(dto.getSubtitulo());

        if (dto.getDescripcionCorta() != null)
            entity.setDescripcionCorta(dto.getDescripcionCorta());

        if (dto.getDescripcionLarga() != null)
            entity.setDescripcionLarga(dto.getDescripcionLarga());

        if (dto.getUbicacion() != null)
            entity.setUbicacion(dto.getUbicacion());

        if (dto.getMapaUrl() != null)
            entity.setMapaUrl(dto.getMapaUrl());

        if (dto.getImagenPrincipal() != null)
            entity.setImagenPrincipal(dto.getImagenPrincipal());

        if (dto.getGaleria() != null)
            entity.setGaleria(dto.getGaleria());
    }
}
