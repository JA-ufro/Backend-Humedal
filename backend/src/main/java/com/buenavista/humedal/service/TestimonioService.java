package com.buenavista.humedal.service;

import com.buenavista.humedal.dto.*;
import com.buenavista.humedal.mapper.TestimonioMapper;
import com.buenavista.humedal.model.Humedal;
import com.buenavista.humedal.model.Testimonio;
import com.buenavista.humedal.repository.HumedalRepository;
import com.buenavista.humedal.repository.TestimonioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestimonioService {

    private final TestimonioRepository testimonioRepository;
    private final HumedalRepository humedalRepository;
    private final TestimonioMapper mapper;

    public List<TestimonioReadDTO> getByHumedal(Long humedalId) {
        return testimonioRepository.findByHumedalId(humedalId)
                .stream()
                .map(mapper::toReadDTO)
                .toList();
    }

    public TestimonioReadDTO create(TestimonioCreateDTO dto) {
        Humedal h = humedalRepository.findById(dto.getHumedalId())
                .orElseThrow(() -> new RuntimeException("Humedal no encontrado"));

        Testimonio entity = mapper.toEntity(dto, h);
        testimonioRepository.save(entity);

        return mapper.toReadDTO(entity);
    }

    public TestimonioReadDTO update(Long id, TestimonioUpdateDTO dto) {
        Testimonio entity = testimonioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonio no encontrado"));

        mapper.updateEntity(entity, dto);

        testimonioRepository.save(entity);

        return mapper.toReadDTO(entity);
    }

    public void delete(Long id) {
        testimonioRepository.deleteById(id);
    }
}
