CREATE TABLE jokes (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(50) DEFAULT 'general',
    likes INTEGER DEFAULT 0,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_jokes_category ON jokes(category);