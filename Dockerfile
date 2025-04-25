FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
COPY --from=build /app/bookstore-infrastructure/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]