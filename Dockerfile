FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package

FROM openjdk:17.0.1-jdk
COPY --from=build /target/servermanagerapp.jar servermanagerapp.jar
ENTRYPOINT ["java", "-jar", "servermanagerapp.jar"]