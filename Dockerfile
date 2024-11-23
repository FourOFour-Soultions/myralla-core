FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /usr/src/app

# Copy the pom.xml file first to leverage Docker cache
COPY ./pom.xml /usr/src/app/pom.xml

# Copy the rest of the application code
COPY . /usr/src/app/

# Skip tests and build the application
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17-jdk-alpine AS application
RUN apk add tzdata curl
RUN cp /usr/share/zoneinfo/Asia/Manila /etc/localtime
RUN echo "Asia/Manila" > /etc/timezone
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the built JAR file from the build stage
COPY --from=build /usr/src/app/target/myralla-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Duser.timezone=Asia/Manila","-Dlog4j2.formatMsgNoLookups=true","-jar","/app.jar"]