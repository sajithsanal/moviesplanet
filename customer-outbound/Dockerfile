FROM gradle:5.6.4-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle customer-outbound:clean customer-outbound:bootJar --no-daemon

FROM openjdk:11-jre-slim

RUN mkdir /app

COPY --from=build /home/gradle/src/customer-outbound/build/libs/*.jar  /app/customer-outbound.jar


ENTRYPOINT ["java", "-jar", "/app/customer-outbound.jar"]