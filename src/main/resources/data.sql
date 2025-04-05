-- Only insert if the table is empty
INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'Why don''t scientists trust atoms? Because they make up everything!', 'Science Enthusiast', 'puns', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes LIMIT 1);

INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'What do you call a fake noodle? An impasta!', 'Pasta Lover', 'puns', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes WHERE id > 1 LIMIT 1);

INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'Why did the scarecrow win an award? Because he was outstanding in his field!', 'Farm Humor', 'dad jokes', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes WHERE id > 2 LIMIT 1);

INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'What do you call a bear with no teeth? A gummy bear!', 'Wildlife Joker', 'dad jokes', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes WHERE id > 3 LIMIT 1);

INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'Knock, knock. Who''s there? Lettuce. Lettuce who? Lettuce in, it''s cold out here!', 'Vegetable Fan', 'knock-knock', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes WHERE id > 4 LIMIT 1);

INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'I''m reading a book on anti-gravity. It''s impossible to put down!', 'Physics Buff', 'one-liners', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes WHERE id > 5 LIMIT 1);

INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'I used to play piano by ear, but now I use my hands.', 'Music Enthusiast', 'one-liners', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes WHERE id > 6 LIMIT 1);

INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'Why don''t eggs tell jokes? They''d crack up!', 'Breakfast Club', 'dad jokes', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes WHERE id > 7 LIMIT 1);

INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'What do you call a bear with no teeth and no fur? A gummy bear!', 'Wildlife Joker', 'dad jokes', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes WHERE id > 8 LIMIT 1);

INSERT INTO jokes (content, author, category, likes, created_at, updated_at)
SELECT 'I''m on a seafood diet. I see food and I eat it!', 'Foodie', 'one-liners', 0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM jokes WHERE id > 9 LIMIT 1);