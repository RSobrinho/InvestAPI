# Etapa 1: Build da aplicação com Maven (usando uma imagem com Maven e JDK)
# Imagem customizada para build com JDK 21 e Maven
FROM eclipse-temurin:21 AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app

# Copia o pom.xml para aproveitar cache das dependências
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src/ src/

# Compila o projeto e gera o JAR (pule os testes se desejar)
RUN mvn clean package -DskipTests


# Etapa 2: Imagem final baseada no eclipse-temurin:21
FROM eclipse-temurin:21
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta em que a aplicação irá rodar (por exemplo, 8080)
EXPOSE 8080

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]