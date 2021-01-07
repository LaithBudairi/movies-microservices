FROM openjdk:11
ADD /target/gateway-service.jar gateway-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]