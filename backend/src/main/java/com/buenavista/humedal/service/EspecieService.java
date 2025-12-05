package com.buenavista.humedal.service;

import com.buenavista.humedal.dto.*;
import com.buenavista.humedal.mapper.EspecieMapper;
import com.buenavista.humedal.model.Especie;
import com.buenavista.humedal.model.Humedal;
import com.buenavista.humedal.repository.EspecieRepository;
import com.buenavista.humedal.repository.HumedalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecieService {

    private final EspecieRepository especieRepository;
    private final HumedalRepository humedalRepository;

    public List<EspecieReadDTO> getAll() {
        return especieRepository.findAll()
                .stream()
                .map(EspecieMapper::toReadDTO)
                .toList();
    }

    public List<EspecieReadDTO> getByHumedal(Long id) {
        return especieRepository.findByHumedalId(id)
                .stream()
                .map(EspecieMapper::toReadDTO)
                .toList();
    }

    public EspecieReadDTO create(EspecieCreateDTO dto) {
        Humedal h = humedalRepository.findById(dto.getHumedalId())
                .orElseThrow(() -> new RuntimeException("Humedal no encontrado"));

        Especie e = EspecieMapper.toEntity(dto);
        e.setHumedal(h);

        especieRepository.save(e);
        return EspecieMapper.toReadDTO(e);
    }

    public EspecieReadDTO update(Long id, EspecieUpdateDTO dto) {
        Especie e = especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada"));

        EspecieMapper.updateEntity(e, dto);
        especieRepository.save(e);

        return EspecieMapper.toReadDTO(e);
    }

    public void delete(Long id) {
        especieRepository.deleteById(id);
    }
}

