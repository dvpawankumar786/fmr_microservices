FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/spring-eureka-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
VOLUME /eureka
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app.jar"]