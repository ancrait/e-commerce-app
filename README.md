# e-commerce-app-Ecommerce Microservices Application
This is a pet project demonstrating a microservices architecture built with Java and Spring Boot. The system follows an Event-Driven Architecture using Apache Kafka and covers a full order processing lifecycle, from customer management to payment notifications.

ARCHITECTURE & SERVICES

The project consists of the following modules:
Config Service: Centralized configuration management.
Discovery Service: Service registration and discovery using Netflix Eureka.
Gateway Service: The single entry point into the system.
Customer Service: Managing customer data, including profiles and addresses.
Product Service: Handling the product catalog and inventory levels.
Order Service: Processing orders and orchestrating the purchase workflow.
Payment Service: Processing transactions and communicating with payment gateways.
Notification Service: Sending email notifications to customers via asynchronous Kafka events.


TECH STACK

Java 17+ / Spring Boot 3

Apache Kafka: Message broker for inter-service communication.
    
Databases:
    
PostgreSQL: For relational data such as orders, products, and users.
    
MongoDB: For storing logs or specific document-based data like notification history.
    
Docker & Docker Compose: Infrastructure containerization.
    
Observability:

Zipkin: Distributed tracing for tracking requests across services.
    
MailDev: SMTP server and Web UI for testing email delivery.


QUICK START

Prerequisites:

Docker and Docker Compose installed.

Java 17 installed (for running services locally).

Starting the Infrastructure

Navigate to the docker directory and start the required components (databases, brokers, and monitoring tools):


    cd docker
    docker-compose up -d


This command will spin up:
   
PostgreSQL on port 5433.

pgAdmin (Postgres GUI) on port 5050.

MongoDB on port 27017.

Kafka (with Zookeeper) on port 9092.

Zipkin on port 9411.

MailDev on port 1080 (Web UI).


Running the Services

Each service should be started individually as a standard Spring Boot application. It is recommended to follow this order:

1)config-service

2)discovery-service

3)Remaining business services.


INFRASTRACTURE LINKS:

Eureka Dashboard: http://localhost:8761

Zipkin UI: http://localhost:9411

MailDev UI: http://localhost:1080

PgAdmin: http://localhost:5050 (Email: pgadmin@pgadmin.org, Pass: admin)

Mongo Express: http://localhost:8081

