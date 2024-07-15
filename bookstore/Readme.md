## How to run docker file

1. Essential Files
   * Dockerfile: Defines the Docker image for your Spring Boot application.
   * docker-compose.yml: Defines the services, including the Spring Boot application and PostgreSQL, and their configurations.
   * init.sql: (if you have one) for initializing the PostgreSQL database.
   * Application JAR File: Ensure the JAR file is built and available in the target directory.


2. Steps to Build and Run with Docker Compose

    * Clone the Repository or Extract the Files
        * If sharing via a repository, they should clone the repository.
        * If sharing via a ZIP file, they should extract the files.
        Build and Package the Application

Ensure that the JAR file is in the target directory. If not, they need to build it:

    mvn clean package
    Run Docker Compose

Navigate to the directory containing the docker-compose.yml file.
Build and start the Docker containers:

    docker-compose up --build


### Directory Structure
Ensure the files are structured as follows:

    project-root/
    ├── Dockerfile
    ├── docker-compose.yml
    ├── init.sql
    ├── target/
         └── bookstore-0.0.1-SNAPSHOT.jar
