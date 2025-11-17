FROM eclipse-temurin:17-jre-alpine
RUN mkdir /app
COPY ./build/libs/*.jar /app/learning-platform-service.jar
EXPOSE 8080
WORKDIR /app
CMD java -jar learning-platform-service.jar