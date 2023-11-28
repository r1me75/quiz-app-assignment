# Quiz App Assignment

Deze repository bevat een Dockerized setup voor een full-stack applicatie met een Spring Boot backend en een React frontend. Volg de onderstaande instructies om de applicatie te bouwen en uit te voeren met Docker.

## Vereisten

Docker geïnstalleerd op je machine. Je kunt Docker [hier](https://www.docker.com/products/docker-desktop/) downloaden.

## Instructies voor bouwen en uitvoeren

### 1. Clone de Repository
```bash
git clone https://github.com/r1me75/quiz-app-assignment
cd quiz-app-assignment
```
### 2. Bouw de Docker-images:
```bash
docker-compose build
```
### 3. Voer de Docker-containers uit:
```bash
docker-compose up
```
### 4. Toegang tot de Applicatie:
* De React frontend is bereikbaar op http://localhost:8079.
* De Spring Boot backend is bereikbaar op http://localhost:8080.

Zorg ervoor dat de poorten gespecificeerd in het docker-compose.yml bestand niet al in gebruik zijn op je machine.

### 5. Stoppen van de Applicatie:
```bash
docker-compose down
```
Deze opdracht stopt en verwijdert de actieve containers.