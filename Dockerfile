# Builder
FROM maven:3.8.6-openjdk-18 AS build
WORKDIR /build
COPY src src
COPY pom.xml .
RUN mvn clean package -DskipTests

# Run the application
FROM amazoncorretto:18-alpine
WORKDIR /app
COPY --from=build /build/target/*.jar Account.jar
CMD ["java","-jar","/app/Account.jar"]