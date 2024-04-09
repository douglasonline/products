# Use uma imagem base do OpenJDK 21
FROM openjdk:21-ea-23-jdk-slim

# Define o diretório de trabalho dentro da imagem
WORKDIR /app

# Copia o arquivo JAR da sua aplicação para dentro da imagem
COPY target/Products-0.0.1-SNAPSHOT.jar /app/Products-0.0.1-SNAPSHOT.jar

# Instala o cliente PostgreSQL
RUN apt-get update && apt-get install -y postgresql-client

# Define variáveis de ambiente para configuração do PostgreSQL
ENV DB_URL jdbc:postgresql://postgres-service:5432/products
ENV DB_USERNAME postgres
ENV DB_PASSWORD 1234

# Expõe a porta 8080 para acessar a aplicação
EXPOSE 8080

# Define o comando de inicialização da aplicação
CMD ["java", "-jar", "Products-0.0.1-SNAPSHOT.jar"]