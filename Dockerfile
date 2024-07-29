# Usar uma imagem base do OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho na imagem
WORKDIR /app

# Copiar o JAR da aplicação para o diretório de trabalho
COPY target/itau-1.0.0.jar itau-1.0.0.jar

# Expor a porta em que a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "itau-1.0.0.jar"]