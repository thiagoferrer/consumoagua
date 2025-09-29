package br.com.thiago.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResidenciaRequestDTO {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String cep;
    private String matricula;
    private String categoria;
    private String status;
    private LocalDate ultimaLeituraData;
    private Double ultimaLeituraConsumo;
    private String ultimaLeituraMedidor;
}
