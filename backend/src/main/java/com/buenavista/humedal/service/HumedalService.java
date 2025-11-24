package com.buenavista.humedal.service;

import com.buenavista.humedal.dto.*;
import com.buenavista.humedal.mapper.HumedalMapper;
import com.buenavista.humedal.model.Humedal;
import com.buenavista.humedal.repository.HumedalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HumedalService {

    private final HumedalRepository humedalRepository;

    public List<HumedalReadDTO> getAll() {
        return humedalRepository.findAll()
                .stream()
                .map(HumedalMapper::toReadDTO)
                .toList();
    }

    public HumedalReadDTO getById(Long id) {
        Humedal h = humedalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Humedal no encontrado"));
        return HumedalMapper.toReadDTO(h);
    }

    public HumedalReadDTO create(HumedalCreateDTO dto) {
        Humedal h = HumedalMapper.toEntity(dto);
        humedalRepository.save(h);
        return HumedalMapper.toReadDTO(h);
    }

    public HumedalReadDTO update(Long id, HumedalUpdateDTO dto) {
        Humedal h = humedalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Humedal no encontrado"));

        HumedalMapper.updateEntity(h, dto);
        humedalRepository.save(h);

        return HumedalMapper.toReadDTO(h);
    }

    public void delete(Long id) {
        humedalRepository.deleteById(id);
    }
}
