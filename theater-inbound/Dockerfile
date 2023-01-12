FROM gradle:5.6.4-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle theater-inbound:clean theater-inbound:bootJar --no-daemon

FROM openjdk:11-jre-slim

RUN mkdir /app

COPY --from=build /home/gradle/src/theater-inbound/build/libs/*.jar  /app/theater-inbound.jar


ENTRYPOINT ["java", "-jar", "/app/theater-inbound.jar"]