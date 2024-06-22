Study Project. Simple Web app with Thymeleaf HTML Templates, Spring Boot Application and Postgres Database. Deployable on Kubernetes.

It's still under development and not yet finished.

# Job Hunter

## Introduction and Goals

This application is a job search web application developed with Spring Boot, JPA, and PostgreSQL. It allows users to save and manage job offers.  

## Architecture Concepts

The application follows the Model-View-Controller (MVC) pattern:

**Model:** The model represents the data and business logic of the application. In this case, the model is `JobOffer`, which represents the data and behavior of a job offer.  

**View:** The view is responsible for presenting the data of the model. In this application, the views are HTML templates created with Thymeleaf.  

**Controller:** The controller processes user requests, interacts with the model, and updates the view. In this application, `JobController` is the controller.  

The application also uses the principle of Inversion of Control (IoC) and Dependency Injection (DI) through the use of Spring Boot. This allows for loose coupling and easy testability.  

## Deployment and Operation

The application can be deployed in a Kubernetes cluster using Docker and Kubernetes manifests.

Here are the steps to deploy and start the application on a local k8s cluster using minikube:

1. Create the Docker image for the application:

```bash
docker build -t jpl-ferrari-jobhunt-springboot:latest .
```

2. Start Minikube:

```bash
minikube start
```

3. Set the Docker environment to Minikube:

- Linux / Mac
```bash
eval $(minikube docker-env)
```
- Windows / Powershell
```bash
minikube -p minikube docker-env | Invoke-Expression
```

4. Deploy the PostgreSQL database:

```bash
kubectl apply -f k8s_init-db.yaml
kubectl apply -f k8s_manifest_postgres.yaml
```

5. Deploy the Spring Boot application:

```bash
kubectl apply -f k8s_manifest_springboot.yaml
```
The application should now be running in your Kubernetes cluster and accessible via the NodePort of the Spring Boot Service (here 30080).

If you have trouble accessing the service, you could try testing the service directly from the Minikube cluster. You can do this with the command
```bash
minikube service springboot
```
which should open the service in your default web browser.

## Code

The code of the application is written in Java and uses the Spring Boot Framework. The application uses JPA for data persistence and PostgreSQL as a database. The code is divided into various packages that represent different aspects of the application:  
- `com.example.jplferrarijobhunt.controller:` Contains the controller of the application, which processes user requests.  
- `com.example.jplferrarijobhunt.model:` Contains the model of the application, which represents the data and behavior of a job offer.  
- `com.example.jplferrarijobhunt.repository:` Contains the repository, which encapsulates the interaction with the database.  
- `com.example.jplferrarijobhunt.service:` Contains the service, which implements the business logic of the application.  
The application uses Gradle as a build tool and can be built with the command ./gradlew build.