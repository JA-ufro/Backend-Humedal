package com.buenavista.humedal.controller;

import com.buenavista.humedal.model.Humedal;
import com.buenavista.humedal.service.HumedalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/humedales")
@CrossOrigin(origins = "http://localhost:5173") // Permite peticiones desde tu frontend en desarrollo
public class HumedalController {

    private final HumedalService humedalService;

    public HumedalController(HumedalService humedalService) {
        this.humedalService = humedalService;
    }

    @GetMapping
    public List<Humedal> getAllHumedales() {
        return humedalService.getAllHumedales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Humedal> getHumedalById(@PathVariable Long id) {
        return humedalService.getHumedalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
