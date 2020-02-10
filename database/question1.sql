-- Who checked out the book 'The Hobbit’?
SELECT m.* FROM member m
INNER JOIN checkout_item ci
    ON m.id = ci.member_id
INNER JOIN book bo
    ON ci.book_id = bo.id
WHERE bo.title = 'The Hobbit';