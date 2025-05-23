# Java Spring Boot Jokes App

[![Blossom Badge](https://img.boltops.com/images/blossom/logos/blossom-readme.png)](https://blossom-cloud.com)

A ready-to-deploy Java Spring Boot application for managing and displaying jokes. This app is designed to be deployed on [Blossom](https://blossom-cloud.com).

## Quick Start

```bash
# Build the application
mvn clean package

# Run the application
mvn spring-boot:run
```

Visit `http://localhost:8080` in your browser to see the application.

## Database Configuration

The application uses PostgreSQL by default. You can configure the database connection using the `DATABASE_URL` environment variable:

```bash
# For local development
export DATABASE_URL=postgres://postgres:postgres@localhost:5432/jokes_dev

# For production on Blossom, this will be automatically set
```

<details>
<summary>Additional Information</summary>

## Features

- View a list of all jokes
- Add, edit, and delete jokes
- Get a random joke
- Categorize jokes
- RESTful API endpoints

## API Endpoints

- `GET /api/jokes` - Get all jokes
- `GET /api/jokes/{id}` - Get a specific joke
- `GET /api/jokes/random` - Get a random joke
- `POST /api/jokes` - Create a new joke
- `PUT /api/jokes/{id}` - Update a joke
- `DELETE /api/jokes/{id}` - Delete a joke

</details>