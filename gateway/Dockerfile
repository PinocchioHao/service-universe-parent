FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/gateway-0.0.1-SNAPSHOT.jar /app/gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gateway.jar"]
