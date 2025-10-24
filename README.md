# Smarthome Monitoring Application

# README — Quick Start Guide

A Spring Boot + Thymeleaf web app for controlling Light, Fan, and Air Conditioner devices, including an annual scheduled shutdown feature.

---

### Requirements

* Java **17+**
* Docker + Docker Compose
* Git (optional)

---

###  Getting Started

#### Clone the repository (or unzip project)

```sh
git clone https://github.com/carterdboyle/smarthome.git
cd smarthome
```

#### Start PostgreSQL via Docker Compose

```sh
docker compose up -d
```

This will start:

* PostgreSQL database on port **5332**

If the application crashes it will be due to a missing database, run:

```sh
docker exec -it postgres-smarthome bash
```

then from inside the container

```sh
psql -U postgres
```

from the PostgreSQL command-line:

```sh
CREATE DATABASE smarthome;
```

#### Run the application

```sh
./mvnw spring-boot:run
```

### Access the UI

Open in your browser:

-> [http://localhost:8080/devices](http://localhost:8080/devices)

---

### UI Control

Three different types of smart home devices are implemented:
  - Fan
  - Air conditioner
  - Light

The fan is considered off if the select value for SPEED is set to 0.
The AC is considered off if the MODE is set to OFF.
The light is considered off if the OFF button is clicked.

### Testing Features

| Action                               | How                                           |
| ------------------------------------ | --------------------------------------------- |
| **Manual full shutdown**             | Click **TURN ALL OFF** button in UI           |
| **Automatic shutdown test**          | Run with test profile below                   |

### Project Structure

```
src/main/java/com/example/smarthome/
  domain/        # Device types + behavior
  service/       # Business logic + scheduler actions
  web/           # Controllers + UI
  config/        # Scheduler configuration

src/main/resources/
  templates/     # Thymeleaf UI
  static/        # CSS/JS (optional)
  application.properties
  application-test-schedule.properties
docker-compose.yml
```

---

### Default scheduler settings

Runs every year on **Jan 1 @ 01:00 AM local time**
(Zone: `America/Moncton`)

To change schedule → modify:

```
smarthome.update.cron=0 0 1 1 1 *
```

---

### Stopping services

Stop Spring:

```
CTRL + C
```

Stop database:

```sh
docker compose down
```

---

### Optional Dev Tools

If using IntelliJ, verify runtime SDK:
**Project Structure → Project SDK → Java 17+**
