package br.com.thiago.controller;

import br.com.thiago.model.Residencia;
import br.com.thiago.service.ResidenciaService;
import br.com.thiago.repository.ResidenciaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residences")
public class ResidenciaController {

    private final ResidenciaService service;
    private final ResidenciaRepository repository;

    public ResidenciaController(ResidenciaService service, ResidenciaRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // Criar residência
    @PostMapping
    public ResponseEntity<Residencia> create(@RequestBody Residencia r) {
        Residencia criada = service.create(r);
        return ResponseEntity.ok(criada);
    }

    // Atualizar residência
    @PutMapping("/{id}")
    public ResponseEntity<Residencia> update(@PathVariable Long id, @RequestBody Residencia r) {
        Residencia atualizada = service.update(id, r);
        return ResponseEntity.ok(atualizada);
    }

    // Buscar todos
    @GetMapping
    public ResponseEntity<List<Residencia>> getAll() {
        List<Residencia> residencias = repository.findAll();
        return ResponseEntity.ok(residencias);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Residencia> getById(@PathVariable Long id) {
        Residencia r = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Residência não encontrada"));
        return ResponseEntity.ok(r);
    }

    // Deletar residência
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
