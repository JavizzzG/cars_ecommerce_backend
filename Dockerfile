# --- ETAPA 1: BUILD (Compilación) ---
# Usamos una imagen de Java/Maven/Gradle para compilar el código fuente
FROM eclipse-temurin:21-jdk-jammy AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración del proyecto (pom.xml o build.gradle) para resolver dependencias
# Esto aprovecha el caché de Docker si las dependencias no cambian
COPY mvnw .
# Opcional: Asegurar que el script tenga permisos de ejecución
RUN chmod +x mvnw
COPY .mvn .mvn/

COPY pom.xml .
COPY src src

# Copia los archivos del proyecto (si usas Gradle, reemplaza 'pom.xml' con 'build.gradle')

# Comando de compilación (usando Maven wrapper)
# Si usas Gradle, el comando sería './gradlew clean build -x test'
RUN ./mvnw package -DskipTests

# --- ETAPA 2: RUNTIME (Producción) ---
# Usamos una imagen base más ligera (JRE) solo para ejecutar la aplicación
# 17-jre-jammy o 21-jre-jammy es mucho más pequeño que el JDK completo
FROM eclipse-temurin:21-jre-jammy AS final

# Variable de entorno: Spring Boot por defecto usa el puerto 8080.
EXPOSE 8080

# Establece el directorio de trabajo final
WORKDIR /app

# Copia el JAR compilado de la Etapa 1 a la Etapa 2
# El nombre de tu JAR puede variar. Reemplaza 'nombre-de-tu-app' con el nombre real de tu JAR
# Por lo general, se llama como el artifactId de Maven.
COPY --from=build /app/target/*.jar app.jar

# Define el punto de entrada para ejecutar la aplicación
# Usa el parámetro -Djava.security.egd=file:/dev/./urandom para mejorar el rendimiento
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]