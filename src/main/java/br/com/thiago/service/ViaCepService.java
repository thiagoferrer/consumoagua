package br.com.thiago.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping("/{cep}/json/")
    EnderecoViaCep consultarCep(@PathVariable("cep") String cep);

    class EnderecoViaCep {
        public String logradouro;
        public String bairro;
        public String localidade;
        public String uf;
    }
}
