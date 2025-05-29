# HSBC_ASSESSMENT – Full Stack City Counter App

This project is a full-stack web application built for the HSBC assessment. It allows users to enter a letter and retrieve the count of cities starting with that letter, using data from OpenWeatherMap.

It includes:
- Backend: Java 17, Spring Boot 3.5
- Frontend: React (Node.js 18) inside `src/main/frontend`
- Build tool: Maven
- Caching: Spring's `@Cacheable` annotation for improved performance
- Scheduled Task: Auto-clears city count cache every 1 hour using `@Scheduled`
- Containerization: Multi-stage Docker build

---

## Project Structure

```
HSBC_ASSESSMENT/
└── city-counter/
│   ├── src/
│   │   ├── main/
│   │       ├── java/
│   │       │   └── com/arun/immanuel/city_counter/
│   │       │       ├── config/WebConfig.java
│   │       │       ├── controller/CityCounterController.java
│   │       │       ├── handler/GlobalExceptionHandler.java
│   │       │       ├── service/CityCounterService.java
│   │       │       └── CityCounterApplication.java
│   │       ├── resources/
│   │       │   └── application.properties
│   │       └── frontend/
│   │           ├── package.json
│   │           ├── public/
│   │           └── src/
│   │               └── App.jsx
│   │               └── App.css
│   │               └── package.json
│   ├── pom.xml
│   ├── Dockerfile
│   ├── .dockerignore
├── .gitignore
├── README.md
```

---

## Features

- Input a letter and fetch the number of cities starting with that letter
- React frontend is served by Spring Boot backend
- Spring Boot caches the results of each letter-based search using `@Cacheable`
- Cache entries are automatically cleared every hour using `@Scheduled`
- Fully Dockerized, production-ready setup

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
cd city-counter
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
GET /api/cities?letter=a
```

Returns JSON:
```json
{
  "cities":"Zuwarah, Zawiya, Zlitan",
  "count":3
}
```
---

## Caching & Scheduler
Caching: The backend caches results of city counts by starting letter using Spring's @Cacheable.

Scheduler: A scheduled method runs every 1 hour using @Scheduled to clear the cache with @CacheEvict.

Benefits: Improved response time and fewer external API calls.

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
| Caching    | Spring Boot @Cacheable        |
| Scheduler	 | Spring Boot @Scheduled        |
| Docker     | Multi-stage image build       |
