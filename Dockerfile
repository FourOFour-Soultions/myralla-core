# Use the official Maven image to build the application
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /usr/src/app

# Copy the pom.xml file first to leverage Docker cache
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY . .

# Skip tests and build the application
RUN mvn clean install -DskipTests

# Use the official OpenJDK image to run the application
FROM openjdk:17-jdk-alpine
RUN apk add --no-cache tzdata

# Set the timezone
ENV TZ=Asia/Manila

# Add a user to run the application
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the built JAR file from the build stage
COPY --from=build /usr/src/app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-Duser.timezone=Asia/Manila","-Dlog4j2.formatMsgNoLookups=true","-jar","/app.jar"]