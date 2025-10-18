FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/service-driver-booking-0.0.1-SNAPSHOT.jar /app/driver.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "driver.jar"]
