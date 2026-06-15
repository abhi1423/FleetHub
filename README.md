🚚 FleetHub

FleetHub is a Spring Boot-based logistics and fleet management application that enables transporters to manage vehicles, receive transportation requests, and track trip operations. The system provides RESTful APIs for transporter registration, vehicle management, booking workflows, and trip tracking.

📌 Features
Transporter Registration & Management
Vehicle Registration and Availability Management
Vehicle Search by Location and Load Capacity
Transportation Request Management
Request Acceptance/Rejection Workflow
Trip Tracking and Completion
Global Exception Handling
Input Validation using Jakarta Validation
MySQL Database Integration
Layered Architecture (Controller → Service → Repository)
🛠️ Tech Stack
Backend
Java 17+
Spring Boot 3
Spring Web
Spring Data JPA
Hibernate
Database
MySQL
Tools
Maven
IntelliJ IDEA
Git & GitHub
Insomnia/Postman
📂 Project Structure
src/main/java
├── controllers
├── services
├── Repository
├── model
├── DTOs
├── exceptions
├── exceptionDTOs
├── configurations
└── FleetHub.java
🚀 API Highlights
Transporter APIs
Method	Endpoint	Description
POST	/transporter/add	Register Transporter
GET	/transporter/get/{username}	Get Transporter Details
POST	/transporter/add/vehicle/{username}	Add Vehicle
GET	/transporter/get/vehicle/{vehicleNumber}	Get Vehicle Details
PUT	/transporter/update/availabilityOfVehicle/{username}/{vehicleNumber}	Update Vehicle Availability
Booking APIs
Method	Endpoint	Description
POST	/transporter/bookMyVehicle/{vehicleNumber}	Create Transportation Request
GET	/transporter/get/loadRequests/{username}	View Requests
PUT	/transporter/requests/acceptance/{requestId}/{status}	Accept/Reject Request
⚙️ Setup & Installation
Clone Repository
git clone https://github.com/abhi1423/FleetHub.git
cd FleetHub
Configure Database

Update application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/fleethub
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Build Project
mvn clean install
Run Application
mvn spring-boot:run

🧩 Key Functionalities
Vehicle Management
Register new vehicles
Track availability
Search vehicles based on city and load capacity
Request Management
Consumers can request vehicles
Transporters can accept or reject requests
Request status updates handled through APIs
Trip Management
Start transportation trips
Update trip status
Complete trips and update vehicle availability
🔒 Validation & Error Handling

Implemented:

Custom Exception Classes
Global Exception Handler
Request Validation using Jakarta Validation
📈 Future Enhancements
JWT Authentication & Authorization
Spring Security Integration
Microservices Architecture
Apache Kafka Event Streaming
Docker Containerization
API Gateway
Service Discovery (Eureka)
Circuit Breaker & Fallback Mechanisms
CI/CD Pipeline Integration
👨‍💻 Author

Abhinav Chandel

🔗 GitHub: https://github.com/abhi1423
