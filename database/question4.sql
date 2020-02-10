-- Add the book 'The Pragmatic Programmer'
INSERT INTO book (title)
VALUES ('The Pragmatic Programmer');

-- Add yourself as a member
INSERT INTO member (name)
VALUES ('Thomaz Zandonotto');

-- Check out 'The Pragmatic Programmer'.
INSERT INTO checkout_item (member_id, book_id)
VALUES (
    (SELECT m.id FROM member m WHERE m.name = 'Thomaz Zandonotto'),
    (SELECT bo.id FROM book bo WHERE bo.title = 'The Pragmatic Programmer')
);

COMMIT;

-- Use your query from question 1 to verify that you have checked it out.
SELECT m.* FROM member m
INNER JOIN checkout_item ci
    ON m.id = ci.member_id
INNER JOIN book bo
    ON ci.book_id = bo.id
WHERE bo.title = 'The Pragmatic Programmer';