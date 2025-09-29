FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar o JAR
COPY target/consumo-agua.jar app.jar

# Expor porta
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-Dserver.port=$PORT", "-jar", "app.jar"]