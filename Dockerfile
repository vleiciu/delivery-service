FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8084
ARG JAR_FILE=target/DeliveryService-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} DeliveryService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/DeliveryService-0.0.1-SNAPSHOT.jar"]