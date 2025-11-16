package com.buenavista.humedal.service;


import com.buenavista.humedal.model.Humedal;
import com.buenavista.humedal.repository.HumedalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HumedalService {

    private final HumedalRepository humedalRepository;

    public HumedalService(HumedalRepository humedalRepository) {
        this.humedalRepository = humedalRepository;
    }

    public List<Humedal> getAllHumedales() {
        return humedalRepository.findAll();
    }

    public Optional<Humedal> getHumedalById(Long id) {
        return humedalRepository.findById(id);
    }
}
