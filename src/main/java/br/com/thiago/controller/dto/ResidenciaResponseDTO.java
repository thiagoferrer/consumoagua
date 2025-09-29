package br.com.thiago.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResidenciaResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String matricula;
    private String categoria;
    private String status;
    private LocalDate dataCadastro;
    private LocalDate ultimaLeituraData;
    private Double ultimaLeituraConsumo;
    private String ultimaLeituraMedidor;
}
