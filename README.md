# Consumo Água API

Uma API RESTful para gerenciamento de consumo de água e cadastro de residências, desenvolvida em Java com Spring Boot.

## 📋 Sobre o Projeto

Esta aplicação fornece endpoints para:
- Cadastro e gerenciamento de residências
- Registro de leituras de medidores de água
- Integração com ViaCEP para preenchimento automático de endereços
- Controle de consumo de água

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Web**
- **Spring Cloud OpenFeign**
- **Lombok**
- **H2 Database** (ou outro banco configurável)
- **Maven**

## 📦 Estrutura do Projeto

```
consumoaqua/
├── src/
│   └── main/
│       └── java/
│           └── br.com.thiago/
│               ├── controller/
│               │   ├── AquaController.java
│               │   └── ResidenciaController.java
│               ├── model/
│               │   ├── Endereco.java
│               │   ├── LeituraDoMedidor.java
│               │   └── Residencia.java
│               ├── repository/
│               │   ├── LeituraDoMedidorRepository.java
│               │   └── ResidenciaRepository.java
│               ├── service/
│               │   ├── impl/
│               │   │   └── AquaServiceImpl.java
│               │   ├── AguaService.java
│               │   ├── ResidenciaService.java
│               │   └── ViaCepService.java
│               └── ConsumoAguaApplication.java
├── resources/
└── pom.xml
```

## 🛠️ Configuração e Instalação

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- Git

### Clone e Execução

```bash
# Clone o repositório
git clone <url-do-repositorio>

# Navegue até o diretório
cd consumoaqua

# Compile o projeto
mvn clean compile

# Execute a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📡 Endpoints da API

### Residencias (`/api/residences`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/residences` | Lista todas as residências |
| GET | `/api/residences/{id}` | Busca residência por ID |
| POST | `/api/residences` | Cria uma nova residência |
| PUT | `/api/residences/{id}` | Atualiza uma residência |
| DELETE | `/api/residences/{id}` | Remove uma residência |

### Água (`/api/agua`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/agua` | Lista todas as residências |
| GET | `/api/agua/{id}` | Busca residência por ID |
| POST | `/api/agua` | Cria uma nova residência |
| PUT | `/api/agua/{id}` | Atualiza uma residência |
| DELETE | `/api/agua/{id}` | Remove uma residência |

## 📋 Modelos de Dados

### Residencia
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "email": "joao@email.com",
  "telefone": "(11) 99999-9999",
  "logradouro": "Rua das Flores",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01001-000",
  "matricula": "12345",
  "categoria": "Residencial",
  "status": "Ativo",
  "dataCadastro": "2024-01-15",
  "ultimaLeituraData": "2024-01-15",
  "ultimaLeituraConsumo": 150.5,
  "ultimaLeituraMedidor": "A123"
}
```

### LeituraDoMedidor
```json
{
  "id": 1,
  "residencia": {
    "id": 1
  },
  "timestamp": "2024-01-15T10:30:00",
  "consumption": 150.5
}
```

## 🔌 Integração com ViaCEP

A aplicação integra-se automaticamente com o serviço ViaCEP para preencher os dados de endereço a partir do CEP informado.

## 🗃️ Banco de Dados

Por padrão, a aplicação utiliza o banco H2 em memória. Para configurar outro banco de dados, edite o arquivo `application.properties`:

```properties
# Exemplo para PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/consumoaqua
spring.datasource.username=usuario
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

## 🧪 Testes

Para executar os testes:

```bash
mvn test
```

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 🤝 Contribuição

Contribuições são sempre bem-vindas! Por favor, leia as diretrizes de contribuição antes de enviar um pull request.

---

**Desenvolvido por Thiago**
