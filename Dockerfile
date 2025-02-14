FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia os arquivos necessários
COPY pom.xml .
COPY src ./src

# Builda o projeto
RUN mvn clean install

FROM openjdk:17-jdk
WORKDIR /app

# Define um argumento para a versão
ARG JAR_VERSION

# Exibe a versão passada como argumento
RUN echo "Usando versão do JAR: ${JAR_VERSION}"

# Copia o JAR usando a versão especificada
COPY --from=builder /app/target/fiapeats-${JAR_VERSION}.jar app.jar

CMD ["java", "-jar", "app.jar"]
