FROM adoptopenjdk/openjdk11:alpine
LABEL mantainer="Marcelo Pereira"
WORKDIR /app    
EXPOSE 8090
COPY ./target/*.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]