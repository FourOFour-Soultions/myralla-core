FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /usr/src/app
COPY . /usr/src/app/
COPY ./pom.xml /usr/src/app/pom.xml

# Skip tests
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17-jdk-alpine AS application
RUN apk add tzdata curl
RUN cp /usr/share/zoneinfo/Asia/Manila /etc/localtime
RUN echo "Asia/Manila" > /etc/timezone
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /usr/src/app/target/myralla-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Duser.timezone=Asia/Manila","-Dlog4j2.formatMsgNoLookups=true","-jar","/app.jar"]