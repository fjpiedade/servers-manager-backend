FROM openjdk:17
ADD target/servermanagerapp.jar servermanagerapp.jar
ENTRYPOINT ["java", "-jar", "/servermanagerapp.jar"]