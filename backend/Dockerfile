FROM maven:latest AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

FROM openjdk:21
ARG JAR_FILE=*.jar
WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]