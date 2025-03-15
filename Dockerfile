FROM eclipse-temurin:21.0.6_7-jdk-alpine-3.21

WORKDIR /root

EXPOSE 8080

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

RUN ./mvnw dependency:go-offline

COPY ./src /root/src

RUN ./mvnw clean install -DskipTests

ENTRYPOINT ["java", "-jar", "/root/target/El_Sol-0.0.1-SNAPSHOT.jar"]