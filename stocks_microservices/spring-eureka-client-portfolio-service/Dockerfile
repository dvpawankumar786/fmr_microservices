FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/spring-eureka-client-portfolio-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS="-Xms64m -Xmx64m"
VOLUME /portfolio
EXPOSE 9094
ENTRYPOINT ["java","-jar","/app.jar"]