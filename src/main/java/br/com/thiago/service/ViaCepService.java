package br.com.thiago.service;

import br.com.thiago.service.exception.ViaCepException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws", fallback = ViaCepService.Fallback.class)
public interface ViaCepService {

    @GetMapping("/{cep}/json/")
    EnderecoViaCep consultarCep(@PathVariable("cep") String cep);

    @Slf4j
    @Component
    class Fallback implements ViaCepService {
        @Override
        public EnderecoViaCep consultarCep(String cep) {
            log.warn("Fallback acionado para CEP: {}", cep);
            throw new ViaCepException("Serviço de CEP indisponível", null);
        }
    }

    class EnderecoViaCep {
        public String logradouro;
        public String bairro;
        public String localidade;
        public String uf;
        public String erro;
    }
}
