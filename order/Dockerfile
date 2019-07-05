FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG target/order.jar
COPY target/order.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"] 