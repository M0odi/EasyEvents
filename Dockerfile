FROM openjdk:17-jdk-bullseye

WORKDIR /app

COPY target/EventEasy-0.0.1-SNAPSHOT.jar /app/main.jar

CMD ["java", "-jar", "main.jar"]
