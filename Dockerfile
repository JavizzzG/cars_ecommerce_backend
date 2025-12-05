# ===========================
# 1. Build stage
# ===========================
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copiamos todo el proyecto (incluyendo mvnw)
COPY . .

# Damos permisos al wrapper
RUN chmod +x mvnw

# Ejecutamos la compilación
RUN ./mvnw clean package -DskipTests

# ===========================
# 2. Runtime stage
# ===========================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]