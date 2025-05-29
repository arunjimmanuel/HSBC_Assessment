# HSBC_ASSESSMENT – Full Stack City Counter App

This project is a full-stack web application built for the HSBC assessment. It allows users to enter a letter and retrieve the count of cities starting with that letter, using data from OpenWeatherMap.

It includes:
- Backend: Java 17, Spring Boot 3.5
- Frontend: React (Node.js 18) inside `src/main/frontend`
- Build tool: Maven
- Containerization: Multi-stage Docker build

---

## Project Structure

```
HSBC_ASSESSMENT/
└── city-counter/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/arun/immanuel/city_counter/
    │   │   │       ├── config/WebConfig.java
    │   │   │       ├── controller/CityCounterController.java
    │   │   │       ├── service/CityCounterService.java
    │   │   │       └── CityCounterApplication.java
    │   │   ├── resources/
    │   │   │   └── application.properties
    │   │   └── frontend/
    │   │       ├── package.json
    │   │       ├── public/
    │   │       └── src/
    │   │           └── App.jsx
    ├── pom.xml
    ├── Dockerfile
    ├── .dockerignore
    README.md
```

---

## Features

-  Input a letter and fetch the number of cities starting with that letter
-  React frontend is served by Spring Boot backend
-  Fully Dockerized, production-ready setup

---

## Requirements

- Java 17+
- Maven 3.8+
- Docker (if running in container)
- Node.js 18 (only needed if running frontend locally for development)

---

## How to Build and Run

### Build with Maven (Frontend + Backend)

```bash
cd HSBC_ASSESSMENT/city-counter
mvn clean install
mvn spring-boot:run
```

Open in browser:  
http://localhost:8080

---

### 🐳 Run with Docker

#### 1. Build the Docker image

```bash
docker build -t city-counter-app .
```

#### 2. Run the container

```bash
docker run -p 8080:8080 city-counter-app
```

Then open:  
 http://localhost:8080

---

## API Endpoint

```
GET /api/count?letter=a
```

Returns JSON:
```json
{
  "count": 3
}
```

---

## Development Mode for Frontend

If you want to run the React app separately using `npm start`, add this to `src/main/frontend/package.json`:

```json
"proxy": "http://localhost:8080"
```

This allows the React app (port 3000) to call Spring Boot backend (port 8080) without CORS issues.

---

## Technologies Used

| Layer      | Tech                          |
|------------|-------------------------------|
| Backend    | Java 17, Spring Boot 3.5      |
| Frontend   | React, Node.js 18             |
| Build Tool | Maven                         |
| Docker     | Multi-stage image build       |
