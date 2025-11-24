package com.buenavista.humedal.controller;

import com.buenavista.humedal.dto.*;
import com.buenavista.humedal.service.HumedalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/humedales")
public class HumedalController {

    private final HumedalService humedalService;

    @GetMapping
    public List<HumedalReadDTO> getAll() {
        return humedalService.getAll();
    }

    @GetMapping("/{id}")
    public HumedalReadDTO getOne(@PathVariable Long id) {
        return humedalService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public HumedalReadDTO create(@RequestBody HumedalCreateDTO dto) {
        return humedalService.create(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public HumedalReadDTO update(@PathVariable Long id, @RequestBody HumedalUpdateDTO dto) {
        return humedalService.update(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        humedalService.delete(id);
    }
}
