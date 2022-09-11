# Builder
FROM maven:3.8.6-openjdk-18 AS build
WORKDIR /build
COPY src src
COPY pom.xml .
COPY docker-entrypoint.sh .
RUN mvn clean package -DskipTests

# Run the application
FROM amazoncorretto:18-alpine
WORKDIR /app
COPY --from=build /build/target/*.jar Account.jar
COPY --from=build /build/docker-entrypoint.sh docker-entrypoint.sh
RUN ["chmod", "+x", "/app/docker-entrypoint.sh"]
ENTRYPOINT [ "/app/docker-entrypoint.sh" ]