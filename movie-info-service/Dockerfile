FROM openjdk:11
ADD target/movie-info-service.jar movie-info-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "movie-info-service.jar"]