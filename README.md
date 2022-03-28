# Trendyol link converter

## Description

Link Converter is a SpringBoot based web application aimed to convert links of different types.

## Production build

Prerequisites:

- docker
- JDK 17

Steps:

```bash
./gradlew bootJar
docker build -t link-converter .
```

## Build and run application locally

### With docker compose

Prerequisites:

- docker compose

Launch steps:

```bash
docker-compose up -d
```

### With docker

Prerequisites:

- docker
- JDK 17 (optional)

Launch steps:

```bash
./gradlew bootJar
docker build -t link-converter .
docker run -p 8080:8080 -d --name link-converter link-converter
```

OR

```bash
docker build -t link-converter -f Dockerfile-local .
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev -d --name link-converter link-converter
```

Ensure you have mongodb available on `mongodb:27017` and redis on `redis:6379`

### Without docker

Prerequisites:

- JDK 17
- MongoDB 5
- Redis 6

```bash
./gradlew bootJar
java -jar build/libs/link-converter-0.0.1-SNAPSHOT.jar
```

Ensure you have mongodb available on `localhost:27017` and redis on `localhost:6379`

## API documentation

After the application is running, you can access the API documentation by pointing your browser
to `http://localhost:8080/swagger-ui.html`