#!/bin/bash

set -eu

# Function to parse DATABASE_URL into components
parse_db_url() {
    local url=$1

    if [[ $url =~ ^postgres://([^:]+):([^@]+)@([^:]+):([0-9]+)/([^?]+)$ ]]; then
        # Remote URL format with password: postgres://username:password@host:port/database
        export DATABASE_USERNAME="${BASH_REMATCH[1]}"
        export DATABASE_PASSWORD="${BASH_REMATCH[2]}"
        export DATABASE_HOST="${BASH_REMATCH[3]}"
        export DATABASE_PORT="${BASH_REMATCH[4]}"
        export DATABASE_NAME="${BASH_REMATCH[5]}"
    elif [[ $url =~ ^postgres://([^@]+)@([^:]+):([0-9]+)/([^?]+)$ ]]; then
        # Local URL format without password: postgres://username@host:port/database
        export DATABASE_USERNAME="${BASH_REMATCH[1]}"
        export DATABASE_PASSWORD="postgres"
        export DATABASE_HOST="${BASH_REMATCH[2]}"
        export DATABASE_PORT="${BASH_REMATCH[3]}"
        export DATABASE_NAME="${BASH_REMATCH[4]}"
    else
        echo "Error: Invalid DATABASE_URL format"
        echo "Expected format: postgres://username[:password]@host:port/database"
        exit 1
    fi

    # Build JDBC URL (always use postgresql:// for JDBC)
    export JDBC_DATABASE_URL="jdbc:postgresql://$DATABASE_HOST:$DATABASE_PORT/$DATABASE_NAME"
}

echo "Setting up database..."
echo "DATABASE_URL: $DATABASE_URL"

# Parse the DATABASE_URL
parse_db_url "$DATABASE_URL"

# Debug output
echo "Host: $DATABASE_HOST"
echo "Port: $DATABASE_PORT"
echo "Database: $DATABASE_NAME"
echo "Username: $DATABASE_USERNAME"
echo "JDBC URL: $JDBC_DATABASE_URL"

# Check if flyway_schema_history exists
echo "Checking database state..."
SCHEMA_HISTORY_EXISTS=$(PGPASSWORD="$DATABASE_PASSWORD" psql "$DATABASE_URL" -t -c "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'flyway_schema_history')")

if [ "$SCHEMA_HISTORY_EXISTS" = " t" ]; then
    echo "Found existing Flyway schema history. Current migrations:"
    PGPASSWORD="$DATABASE_PASSWORD" psql "$DATABASE_URL" -c "SELECT version, description, checksum FROM flyway_schema_history ORDER BY installed_rank"
else
    echo "Fresh database detected, no Flyway schema history found"
fi

# Get the script's directory
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
# Get the project root directory (parent of bin)
PROJECT_ROOT="$( cd "$SCRIPT_DIR/.." && pwd )"
# Set the migrations directory
MIGRATIONS_DIR="$PROJECT_ROOT/src/main/resources/db/migration"

echo "Running migrations from $MIGRATIONS_DIR"
# Run Flyway migration using filesystem location
mvn flyway:info flyway:migrate \
    -Dflyway.url="$JDBC_DATABASE_URL" \
    -Dflyway.user="$DATABASE_USERNAME" \
    -Dflyway.password="$DATABASE_PASSWORD" \
    -Dflyway.locations="filesystem:$MIGRATIONS_DIR" \
    -Dflyway.baselineOnMigrate=true \
    -X
