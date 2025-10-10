# Consumo Água API

API para gerenciamento de consumo de água residencial com integração de endereços via ViaCEP.

## 📋 Descrição do Projeto

Sistema desenvolvido para controle e monitoramento do consumo de água em residências, permitindo o cadastro de usuários, consulta automática de endereços via CEP e registro de leituras de medidores.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.11**
- **Spring Data JPA**
- **Spring Cloud OpenFeign**
- **H2 Database** (Desenvolvimento)
- **PostgreSQL** (Produção)
- **SpringDoc OpenAPI** (Documentação)
- **Lombok**
- **Maven**

## 🏗️ Arquitetura

```
src/
├── main/
│   ├── java/
│   │   └── br.com.thiago/
│   │       ├── controller/          # Controladores REST
│   │       ├── dto/                # Objetos de Transferência de Dados
│   │       ├── exception/          # Tratamento de exceções
│   │       ├── model/              # Entidades JPA
│   │       ├── repository/         # Interfaces de repositório
│   │       └── service/            # Lógica de negócio
│   └── resources/
│       └── application.yml         # Configurações
└── test/
```

## 📊 Endpoints Principais

### Residências (`/api/residencias`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/residencias` | Criar nova residência |
| GET | `/api/residencias` | Listar todas as residências |
| GET | `/api/residencias/{id}` | Buscar residência por ID |
| PUT | `/api/residencias/{id}` | Atualizar residência |
| DELETE | `/api/residencias/{id}` | Excluir residência |

### Água (`/api/agua`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/agua` | Criar registro de água |
| GET | `/api/agua` | Listar todos os registros |
| GET | `/api/agua/{id}` | Buscar registro por ID |
| PUT | `/api/agua/{id}` | Atualizar registro |
| DELETE | `/api/agua/{id}` | Excluir registro |

## 🔧 Configuração e Execução

### Pré-requisitos
- Java 17
- Maven 3.6+
- PostgreSQL (para produção)

### Execução Local

1. **Clone o repositório**
```bash
git clone <repository-url>
cd consumo-agua
```

2. **Execute a aplicação**
```bash
mvn spring-boot:run
```

3. **Acesse os endpoints**
- API: http://localhost:8080

- Swagger UI: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console

Interface Swagger:
![Captura de tela_10-10-2025_172944_localhost.jpeg](../../../Users/thi-f/Downloads/Captura%20de%20tela_10-10-2025_172944_localhost.jpeg)
Console H2 Database
![Captura de tela_10-10-2025_17295_localhost.jpeg](../../../Users/thi-f/Downloads/Captura%20de%20tela_10-10-2025_17295_localhost.jpeg)
Dashboard Railway
![Captura de tela_10-10-2025_172925_railway.com.jpeg](../../../Users/thi-f/Downloads/Captura%20de%20tela_10-10-2025_172925_railway.com.jpeg)

### Configurações

**Desenvolvimento (dev):**
- Banco: H2 em memória
- DDL: update
- SQL visível: true

**Produção (prd):**
- Banco: PostgreSQL
- DDL: validate
- SQL visível: false

## 📡 Integrações

### ViaCEP
A API integra com o serviço ViaCEP para preenchimento automático de endereços:
- Consulta de CEP em tempo real
- Fallback para indisponibilidade do serviço
- Validação de CEP existente

## 🛡️ Tratamento de Exceções

A aplicação possui tratamento centralizado de exceções:

- `ResourceNotFoundException` - 404 Not Found
- `BusinessException` - 400 Bad Request
- `ViaCepException` - 503 Service Unavailable
- `Exception` - 500 Internal Server Error

## 📦 Deploy

### Railway
A aplicação está configurada para deploy no Railway:

```toml
[build]
builder = "nixpacks"

[build.environment]
JAVA_VERSION = "17"

[deploy]
startCommand = "java -Dserver.port=$PORT -jar target/consumo-agua.jar"
```

### Variáveis de Ambiente (Produção)

| Variável | Descrição |
|----------|-----------|
| PGHOST | Host do PostgreSQL |
| PGPORT | Porta do PostgreSQL |
| PGDATABASE | Nome do banco de dados |
| PGUSER | Usuário do PostgreSQL |
| PGPASSWORD | Senha do PostgreSQL |
| PORT | Porta da aplicação |

## 🧪 Testes

Execute os testes com:
```bash
mvn test
```

## 📝 Exemplo de Uso

### Criar Residência
```json
POST /api/residencias
{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "email": "joao@email.com",
  "telefone": "(11) 99999-9999",
  "cep": "01001000",
  "matricula": "12345",
  "categoria": "RESIDENCIAL",
  "status": "ATIVO"
}
```

## 👨‍💻 Desenvolvedor

**Thiago Carvalho Ferrer** - Engenheiro de Software

---

## 🔗 Links Úteis

- [Documentação da API](http://localhost:8080/swagger-ui.html)
- [Console H2](http://localhost:8080/h2-console)
- [Railway Dashboard](https://railway.app)

## 📄 Licença

Este projeto está sob licença. Veja o arquivo LICENSE para mais detalhes.