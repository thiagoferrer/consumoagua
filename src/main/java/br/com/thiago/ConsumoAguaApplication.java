package br.com.thiago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  // <--- habilita Feign
public class ConsumoAguaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumoAguaApplication.class, args);
    }
}
