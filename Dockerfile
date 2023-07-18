FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/nome-do-arquivo.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]