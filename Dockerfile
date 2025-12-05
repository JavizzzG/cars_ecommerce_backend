# --- ETAPA 1: BUILD (Compilación) ---
# CAMBIO CLAVE: Usamos una imagen de Maven que ya tiene el comando 'mvn' disponible
FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos solo los archivos fuente y de configuración (no necesitamos mvnw/.mvn)
COPY pom.xml .
COPY src src

# Comando de compilación (usamos 'mvn' en lugar de './mvnw')
RUN mvn clean package -DskipTests

# --- ETAPA 2: RUNTIME (Producción) ---
# Esto puede seguir igual (usando JRE para reducir el tamaño)
FROM eclipse-temurin:21-jre AS final

# ... (El resto del Dockerfile sigue igual)

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]