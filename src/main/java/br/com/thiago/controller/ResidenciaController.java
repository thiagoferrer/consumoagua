package br.com.thiago.controller;

import br.com.thiago.controller.dto.ResidenciaRequestDTO;
import br.com.thiago.controller.dto.ResidenciaResponseDTO;
import br.com.thiago.service.ResidenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residencias")
@RequiredArgsConstructor
public class ResidenciaController {

    private final ResidenciaService residenciaService;

    @PostMapping
    public ResponseEntity<ResidenciaResponseDTO> create(@Valid @RequestBody ResidenciaRequestDTO requestDTO) {
        ResidenciaResponseDTO response = residenciaService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidenciaResponseDTO> update(@PathVariable Long id,
                                                        @Valid @RequestBody ResidenciaRequestDTO requestDTO) {
        ResidenciaResponseDTO response = residenciaService.update(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ResidenciaResponseDTO>> getAll() {
        List<ResidenciaResponseDTO> residencias = residenciaService.findAll();
        return ResponseEntity.ok(residencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidenciaResponseDTO> getById(@PathVariable Long id) {
        ResidenciaResponseDTO response = residenciaService.findById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        residenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
