package com.buenavista.humedal.controller;

import com.buenavista.humedal.dto.*;
import com.buenavista.humedal.service.TestimonioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/testimonios")
public class TestimonioController {

    private final TestimonioService testimonioService;

    @GetMapping("/humedal/{id}")
    public List<TestimonioReadDTO> getByHumedal(@PathVariable Long id) {
        return testimonioService.getByHumedal(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public TestimonioReadDTO create(@RequestBody TestimonioCreateDTO dto) {
        return testimonioService.create(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public TestimonioReadDTO update(@PathVariable Long id, @RequestBody TestimonioUpdateDTO dto) {
        return testimonioService.update(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        testimonioService.delete(id);
    }
}
