-- Seed initial jokes data
INSERT INTO jokes (content, author, category, created_at, updated_at)
VALUES
    ('Why don''t programmers like nature? It has too many bugs.', 'Tech Joker', 'programming', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('What did the grape say when it got stepped on? Nothing, it just let out a little wine.', 'Food Punster', 'puns', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Why did the scarecrow win an award? Because he was outstanding in his field!', 'Dad Joker', 'dad jokes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Knock knock! Who''s there? Interrupting cow. Interrupting cow w- MOO!', 'Classic Comic', 'knock-knock', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Why don''t scientists trust atoms? Because they make up everything!', 'Science Jester', 'science', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('I told my wife she was drawing her eyebrows too high. She looked surprised.', 'Punny Person', 'puns', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('What do you call a bear with no teeth? A gummy bear!', 'Animal Joker', 'one-liners', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Why did the math book look so sad? Because it had too many problems.', 'Math Whiz', 'dad jokes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('What do you call a fake noodle? An impasta!', 'Food Comedian', 'puns', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Time flies like an arrow. Fruit flies like a banana.', 'Wordplay Master', 'one-liners', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (content) DO NOTHING;