FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/service-energy-reporting-0.0.1-SNAPSHOT.jar /app/energy.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "energy.jar"]
