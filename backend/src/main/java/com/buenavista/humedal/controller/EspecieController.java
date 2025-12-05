package com.buenavista.humedal.controller;

import com.buenavista.humedal.dto.*;
import com.buenavista.humedal.service.EspecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/especies")
public class EspecieController {

    private final EspecieService especieService;

    @GetMapping
    public List<EspecieReadDTO> getAll() {
        return especieService.getAll();
    }

    @GetMapping("/humedal/{id}")
    public List<EspecieReadDTO> getByHumedal(@PathVariable Long id) {
        return especieService.getByHumedal(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public EspecieReadDTO create(@RequestBody EspecieCreateDTO dto) {
        return especieService.create(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public EspecieReadDTO update(@PathVariable Long id, @RequestBody EspecieUpdateDTO dto) {
        return especieService.update(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        especieService.delete(id);
    }
}

