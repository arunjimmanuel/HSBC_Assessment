FROM node:18-alpine AS frontend-builder

WORKDIR /app
COPY src/main/frontend ./frontend
WORKDIR /app/frontend

RUN npm install
RUN npm run build


FROM maven:3.9.6-eclipse-temurin-17-alpine AS backend-builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

COPY --from=frontend-builder /app/frontend/build ./src/main/resources/static

RUN mvn clean package -DskipTests -Pskip-frontend


FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp
WORKDIR /app

COPY --from=backend-builder /app/target/city-counter-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
