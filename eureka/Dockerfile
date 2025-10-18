FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/eureka-0.0.1-SNAPSHOT.jar /app/eureka.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka.jar"]
