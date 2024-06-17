# Arc42 Dokumentation

## Einführung und Ziele

Diese Anwendung ist eine Job-Such-Webanwendung, die mit Spring Boot, JPA und PostgreSQL entwickelt wurde. Sie ermöglicht es Benutzern, Jobangebote zu speichern und zu verwalten.

## Architekturkonzepte

Die Anwendung folgt dem Model-View-Controller (MVC) Muster:

- **Model**: Das Modell repräsentiert die Daten und die Geschäftslogik der Anwendung. In diesem Fall ist das Modell `JobOffer`, das die Daten und das Verhalten eines Jobangebots repräsentiert.

- **View**: Die View ist verantwortlich für die Darstellung der Daten des Modells. In dieser Anwendung sind die Views HTML-Templates, die mit Thymeleaf erstellt wurden.

- **Controller**: Der Controller verarbeitet Benutzeranfragen, interagiert mit dem Modell und aktualisiert die View. In dieser Anwendung ist `JobController` der Controller.

Die Anwendung verwendet auch das Prinzip der Inversion of Control (IoC) und Dependency Injection (DI) durch den Einsatz von Spring Boot. Dies ermöglicht eine lose Kopplung und eine einfache Testbarkeit.

## Deployment und Betrieb

Die Anwendung kann in einem Kubernetes-Cluster mit Hilfe von Docker und Kubernetes-Manifesten bereitgestellt werden. Hier sind die Schritte zum Deployen und Starten der Anwendung:

1. Erstellen Sie das Docker-Image für die Anwendung:

```bash
docker build -t jpl-ferrari-jobhunt-springboot:latest .
```

2. Starten Sie Minikube:

```bash
minikube start
```

3. Setzen Sie die Docker-Umgebung auf Minikube:

```bash
eval $(minikube docker-env)
```

4. Deployen Sie die PostgreSQL-Datenbank:

```bash
kubectl apply -f k8s_manifest_postgres.yaml
```

5. Deployen Sie die Spring Boot Anwendung:

```bash
kubectl apply -f k8s_manifest_springboot.yaml
```

Die Anwendung sollte nun in Ihrem Kubernetes-Cluster laufen und über den NodePort des Spring Boot Services erreichbar sein.

## Code

Der Code der Anwendung ist in Java geschrieben und verwendet das Spring Boot Framework. Die Anwendung verwendet JPA für die Datenpersistenz und PostgreSQL als Datenbank. Der Code ist in verschiedene Pakete unterteilt, die verschiedene Aspekte der Anwendung repräsentieren:

- `com.example.jplferrarijobhunt.controller`: Enthält den Controller der Anwendung, der Benutzeranfragen verarbeitet.

- `com.example.jplferrarijobhunt.model`: Enthält das Modell der Anwendung, das die Daten und das Verhalten eines Jobangebots repräsentiert.

- `com.example.jplferrarijobhunt.repository`: Enthält das Repository, das die Interaktion mit der Datenbank kapselt.

- `com.example.jplferrarijobhunt.service`: Enthält den Service, der die Geschäftslogik der Anwendung implementiert.

Die Anwendung verwendet Gradle als Build-Tool und kann mit dem Befehl `./gradlew build` gebaut werden.