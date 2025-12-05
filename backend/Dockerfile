FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar el pom y resolver dependencias primero (mejor cache)
COPY pom.xml .
RUN mvn -q -e -B dependency:go-offline

# Copiar el código fuente y compilar
COPY src ./src

RUN mvn -q -e -B clean package -DskipTests

# Etapa 2: imagen ligera para ejecutar
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Puerto del backend (por defecto 8080)
EXPOSE 8080

# Variables de entorno típicas (puedes sobreescribirlas con docker/docker-compose)
ENV SPRING_PROFILES_ACTIVE=default

ENTRYPOINT ["java", "-jar", "app.jar"]