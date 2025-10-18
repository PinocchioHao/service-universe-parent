FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/service-municipal-parking-0.0.1-SNAPSHOT.jar /app/parking.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "parking.jar"]
