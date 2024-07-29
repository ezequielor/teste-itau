# Usar uma imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Diretório de trabalho na imagem
WORKDIR /app

# Copiar o JAR da aplicação para o diretório de trabalho
COPY target/itau.jar itau.jar

# Expor a porta em que a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "itau.jar"]