FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY libs ./libs
COPY services ./services

RUN mvn -pl services/partner-service -am -DskipTests clean package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY services/partner-service/application-docker.yml /app/
COPY --from=build /app/services/partner-service/target/app.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","app.jar"]
