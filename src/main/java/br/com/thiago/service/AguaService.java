package br.com.thiago.service;

import br.com.thiago.model.Residencia;

import java.util.List;
import java.util.Optional;

public interface AguaService {
    Residencia createResidencia(Residencia residencia);
    Residencia updateResidencia(Residencia residencia);
    List<Residencia> getAllResidencias();
    Optional<Residencia> getResidenciaById(Long id);
    void deleteResidencia(Long id);
}
