FROM openjdk:11

WORKDIR /app
COPY target/com-business-price-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/com-business-price-0.0.1-SNAPSHOT.jar"]
