package br.com.thiago;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
        info = @Info(
                title = "Consumo Água API",
                version = "1.0",
                description = "API para gerenciamento de consumo de água"
        )
)
@EnableFeignClients
@SpringBootApplication
public class ConsumoAguaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumoAguaApplication.class, args);
    }
}