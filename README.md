# Java Spring Boot Jokes App

A ready-to-deploy Java Spring Boot application for managing and displaying jokes. This app is designed to be deployed on [Blossom](https://blossom-cloud.com).

## Features

- View a list of all jokes
- Add, edit, and delete jokes
- Get a random joke
- Categorize jokes
- RESTful API endpoints

## Quick Start

```bash
# Build the application
./mvnw clean package

# Run the application
java -jar target/jokes-app-0.0.1-SNAPSHOT.jar
```

Visit `http://localhost:8080` in your browser to see the application.

## Database Configuration

The application uses PostgreSQL by default. You can configure the database connection using environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/jokes_db
export SPRING_DATASOURCE_USERNAME=your_postgres_user
export SPRING_DATASOURCE_PASSWORD=your_postgres_password
```

Or use an `application.properties` file in the `src/main/resources` directory.

For production deployment on Blossom, the `DATABASE_URL` environment variable will be automatically set.

## API Endpoints

- `GET /api/jokes` - Get all jokes
- `GET /api/jokes/{id}` - Get a specific joke
- `GET /api/jokes/random` - Get a random joke
- `POST /api/jokes` - Create a new joke
- `PUT /api/jokes/{id}` - Update a joke
- `DELETE /api/jokes/{id}` - Delete a joke

## Deployment

This application is configured for deployment on Blossom. The Procfile includes:

- Web process: Runs the Spring Boot application
- Release process: Runs database migrations
