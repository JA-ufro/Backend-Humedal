package com.buenavista.humedal.mapper;

import com.buenavista.humedal.dto.TestimonioCreateDTO;
import com.buenavista.humedal.dto.TestimonioReadDTO;
import com.buenavista.humedal.dto.TestimonioUpdateDTO;
import com.buenavista.humedal.model.Humedal;
import com.buenavista.humedal.model.Testimonio;
import org.springframework.stereotype.Component;

@Component
public class TestimonioMapper {

    // Entity -> ReadDTO
    public TestimonioReadDTO toReadDTO(Testimonio entity) {
        TestimonioReadDTO dto = new TestimonioReadDTO();
        dto.setId(entity.getId());
        dto.setAutor(entity.getAutor());
        dto.setContenido(entity.getTexto());
        dto.setHumedalId(entity.getHumedal().getId());
        dto.setFecha(entity.getFecha());
        return dto;
    }

    // CreateDTO -> Entity
    public Testimonio toEntity(TestimonioCreateDTO dto, Humedal h) {
        Testimonio t = new Testimonio();
        t.setAutor(dto.getAutor());
        t.setTexto(dto.getContenido());
        t.setHumedal(h);
        t.setFecha(dto.getFecha());
        return t;
    }

    // UpdateDTO -> Entity (actualiza)
    public void updateEntity(Testimonio entity, TestimonioUpdateDTO dto) {
        if (dto.getAutor() != null)
            entity.setAutor(dto.getAutor());

        if (dto.getContenido() != null)
            entity.setTexto(dto.getContenido());
    }
}
