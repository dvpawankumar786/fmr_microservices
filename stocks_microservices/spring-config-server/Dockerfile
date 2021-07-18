FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/spring-config-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
VOLUME /config
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]