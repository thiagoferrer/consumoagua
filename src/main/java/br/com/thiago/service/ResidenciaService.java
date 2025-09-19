package br.com.thiago.service;

import br.com.thiago.model.Residencia;
import br.com.thiago.repository.ResidenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ResidenciaService {

    private final ResidenciaRepository residenciaRepository;
    private final ViaCepService viaCepService; // <- Spring não encontrou este bean

    public ResidenciaService(ResidenciaRepository residenciaRepository, ViaCepService viaCepService) {
        this.residenciaRepository = residenciaRepository;
        this.viaCepService = viaCepService;
    }

    @Transactional
    public Residencia create(Residencia r) {
        preencherEnderecoPeloCep(r);
        r.setDataCadastro(LocalDate.now());
        return residenciaRepository.save(r);
    }

    @Transactional
    public Residencia update(Long id, Residencia r) {
        Residencia existente = residenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Residência não encontrada"));

        // atualiza os campos
        existente.setNome(r.getNome());
        existente.setCpf(r.getCpf());
        existente.setEmail(r.getEmail());
        existente.setTelefone(r.getTelefone());
        existente.setMatricula(r.getMatricula());
        existente.setCategoria(r.getCategoria());
        existente.setStatus(r.getStatus());
        existente.setUltimaLeituraData(r.getUltimaLeituraData());
        existente.setUltimaLeituraConsumo(r.getUltimaLeituraConsumo());
        existente.setUltimaLeituraMedidor(r.getUltimaLeituraMedidor());

        // atualiza o CEP e busca endereço
        existente.setCep(r.getCep());
        preencherEnderecoPeloCep(existente);

        return residenciaRepository.save(existente);
    }

    private void preencherEnderecoPeloCep(Residencia r) {
        if (r.getCep() != null && !r.getCep().isBlank()) {
            ViaCepService.EnderecoViaCep endereco = viaCepService.consultarCep(r.getCep());
            r.setLogradouro(endereco.logradouro);
            r.setBairro(endereco.bairro);
            r.setCidade(endereco.localidade);
            r.setEstado(endereco.uf);
        }
    }
}
