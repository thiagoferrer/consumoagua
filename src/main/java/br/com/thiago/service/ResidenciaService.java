package br.com.thiago.service;

import br.com.thiago.controller.dto.ResidenciaRequestDTO;
import br.com.thiago.controller.dto.ResidenciaResponseDTO;
import br.com.thiago.model.Residencia;
import br.com.thiago.repository.ResidenciaRepository;
import br.com.thiago.service.exception.BusinessException;
import br.com.thiago.service.exception.ResourceNotFoundException;
import br.com.thiago.service.exception.ViaCepException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResidenciaService {

    private final ResidenciaRepository residenciaRepository;
    private final ViaCepService viaCepService;

    @Transactional
    public ResidenciaResponseDTO create(ResidenciaRequestDTO requestDTO) {
        try {
            validarResidencia(requestDTO);

            Residencia residencia = new Residencia();
            mapDTOToEntity(requestDTO, residencia);

            preencherEnderecoPeloCep(residencia);
            residencia.setDataCadastro(LocalDate.now());

            Residencia saved = residenciaRepository.save(residencia);
            log.info("Residência criada com ID: {}", saved.getId());

            return mapEntityToResponseDTO(saved);

        } catch (ViaCepException e) {
            throw e;
        } catch (Exception e) {
            log.error("Erro ao criar residência", e);
            throw new BusinessException("Erro ao criar residência: " + e.getMessage());
        }
    }

    @Transactional
    public ResidenciaResponseDTO update(Long id, ResidenciaRequestDTO requestDTO) {
        try {
            Residencia existente = residenciaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Residência não encontrada com ID: " + id));

            validarResidencia(requestDTO);
            mapDTOToEntity(requestDTO, existente);

            if (!existente.getCep().equals(requestDTO.getCep())) {
                preencherEnderecoPeloCep(existente);
            }

            Residencia updated = residenciaRepository.save(existente);
            log.info("Residência atualizada com ID: {}", id);

            return mapEntityToResponseDTO(updated);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (ViaCepException e) {
            throw e;
        } catch (Exception e) {
            log.error("Erro ao atualizar residência ID: {}", id, e);
            throw new BusinessException("Erro ao atualizar residência: " + e.getMessage());
        }
    }

    public List<ResidenciaResponseDTO> findAll() {
        try {
            return residenciaRepository.findAll()
                    .stream()
                    .map(this::mapEntityToResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Erro ao buscar residências", e);
            throw new BusinessException("Erro ao buscar residências");
        }
    }

    public ResidenciaResponseDTO findById(Long id) {
        Residencia residencia = residenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Residência não encontrada com ID: " + id));
        return mapEntityToResponseDTO(residencia);
    }

    @Transactional
    public void delete(Long id) {
        try {
            if (!residenciaRepository.existsById(id)) {
                throw new ResourceNotFoundException("Residência não encontrada com ID: " + id);
            }
            residenciaRepository.deleteById(id);
            log.info("Residência deletada com ID: {}", id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Erro ao deletar residência ID: {}", id, e);
            throw new BusinessException("Erro ao deletar residência");
        }
    }

    private void preencherEnderecoPeloCep(Residencia residencia) {
        try {
            if (residencia.getCep() != null && !residencia.getCep().isBlank()) {
                ViaCepService.EnderecoViaCep endereco = viaCepService.consultarCep(residencia.getCep());
                residencia.setLogradouro(endereco.logradouro);
                residencia.setBairro(endereco.bairro);
                residencia.setCidade(endereco.localidade);
                residencia.setEstado(endereco.uf);
                log.debug("Endereço preenchido pelo CEP: {}", residencia.getCep());
            }
        } catch (Exception e) {
            log.error("Erro ao consultar CEP: {}", residencia.getCep(), e);
            throw new ViaCepException("Erro ao consultar CEP: " + residencia.getCep(), e);
        }
    }

    private void validarResidencia(ResidenciaRequestDTO requestDTO) {
        if (requestDTO.getCep() == null || requestDTO.getCep().isBlank()) {
            throw new BusinessException("CEP é obrigatório");
        }
        if (requestDTO.getNome() == null || requestDTO.getNome().isBlank()) {
            throw new BusinessException("Nome é obrigatório");
        }
        if (requestDTO.getCpf() == null || requestDTO.getCpf().isBlank()) {
            throw new BusinessException("CPF é obrigatório");
        }
    }

    private void mapDTOToEntity(ResidenciaRequestDTO dto, Residencia entity) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setCep(dto.getCep());
        entity.setMatricula(dto.getMatricula());
        entity.setCategoria(dto.getCategoria());
        entity.setStatus(dto.getStatus());
        entity.setUltimaLeituraData(dto.getUltimaLeituraData());
        entity.setUltimaLeituraConsumo(dto.getUltimaLeituraConsumo());
        entity.setUltimaLeituraMedidor(dto.getUltimaLeituraMedidor());
    }

    private ResidenciaResponseDTO mapEntityToResponseDTO(Residencia entity) {
        ResidenciaResponseDTO dto = new ResidenciaResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCpf(entity.getCpf());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setLogradouro(entity.getLogradouro());
        dto.setBairro(entity.getBairro());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        dto.setCep(entity.getCep());
        dto.setMatricula(entity.getMatricula());
        dto.setCategoria(entity.getCategoria());
        dto.setStatus(entity.getStatus());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setUltimaLeituraData(entity.getUltimaLeituraData());
        dto.setUltimaLeituraConsumo(entity.getUltimaLeituraConsumo());
        dto.setUltimaLeituraMedidor(entity.getUltimaLeituraMedidor());
        return dto;
    }
}
