FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY libs ./libs
COPY services ./services

RUN mvn -pl services/booking-service -am -DskipTests clean package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY services/booking-service/application-docker.yml /app/
COPY --from=build /app/services/booking-service/target/app.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","app.jar"]
