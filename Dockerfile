FROM openjdk:21-jdk-slim

# Instalar netcat (nc)
RUN apt-get update && \
    apt-get install -y netcat-openbsd && \
    rm -rf /var/lib/apt/lists/*

ARG JAR_FILE=target/El_Sol-0.0.1-SNAPSHOT.jar

# Copiar el JAR y el script de espera
COPY ${JAR_FILE} app_el_sol.jar
COPY wait-for.sh /wait-for.sh

# Dar permisos de ejecución al script
RUN chmod +x /wait-for.sh

EXPOSE 8080