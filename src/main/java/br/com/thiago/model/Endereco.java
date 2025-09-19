package br.com.thiago.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Endereco {

    private String logradouro;
    private String bairro;
    private String localidade; // cidade
    private String uf;         // estado
    private String cep;
}