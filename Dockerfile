# Use uma imagem com Maven para build
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Build da aplicação
RUN mvn clean package -DskipTests

# Imagem final
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar o JAR do estágio de build
COPY --from=builder /app/target/consumo-agua.jar app.jar

# Expor porta
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]