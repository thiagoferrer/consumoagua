package br.com.thiago.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Residencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    // Dados de endereço
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    private String matricula;
    private String categoria;
    private String status;
    private LocalDate dataCadastro;

    // Última leitura
    private LocalDate ultimaLeituraData;
    private Double ultimaLeituraConsumo;
    private String ultimaLeituraMedidor;
}
