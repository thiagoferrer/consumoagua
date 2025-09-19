package br.com.thiago.service.impl;

import br.com.thiago.model.Residencia;
import br.com.thiago.repository.ResidenciaRepository;
import br.com.thiago.service.AguaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AguaServiceImpl implements AguaService {

    private final ResidenciaRepository residenciaRepository;

    public AguaServiceImpl(ResidenciaRepository residenciaRepository) {
        this.residenciaRepository = residenciaRepository;
    }

    @Override
    public Residencia createResidencia(Residencia residencia) {
        return residenciaRepository.save(residencia);
    }

    @Override
    public Residencia updateResidencia(Residencia residencia) {
        return residenciaRepository.save(residencia);
    }

    @Override
    public List<Residencia> getAllResidencias() {
        return residenciaRepository.findAll();
    }

    @Override
    public Optional<Residencia> getResidenciaById(Long id) {
        return residenciaRepository.findById(id);
    }

    @Override
    public void deleteResidencia(Long id) {
        residenciaRepository.deleteById(id);
    }
}
