package br.com.thiago.controller;

import br.com.thiago.model.Residencia;
import br.com.thiago.service.AguaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agua")
public class AguaController {

    private final AguaService service;

    public AguaController(AguaService service) {
        this.service = service;
    }

    // CORREÇÃO: Remover /api/agua duplicado no PutMapping
    @PutMapping("/{id}")
    public ResponseEntity<Residencia> update(@PathVariable Long id, @RequestBody Residencia residencia) {
        residencia.setId(id);
        return ResponseEntity.ok(service.updateResidencia(residencia));
    }

    // Métodos restantes permanecem iguais...
    @PostMapping
    public ResponseEntity<Residencia> create(@RequestBody Residencia residencia) {
        residencia.setId(null);
        return ResponseEntity.ok(service.createResidencia(residencia));
    }

    @GetMapping
    public ResponseEntity<List<Residencia>> getAll() {
        return ResponseEntity.ok(service.getAllResidencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Residencia> getById(@PathVariable Long id) {
        return service.getResidenciaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteResidencia(id);
        return ResponseEntity.noContent().build();
    }
}