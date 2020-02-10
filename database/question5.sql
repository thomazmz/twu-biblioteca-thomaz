-- Who has checked out more than 1 item?
SELECT m.* from checkout_item
INNER JOIN member m on checkout_item.member_id = m.id
GROUP BY member_id
HAVING count(book_id) + count(movie_id) > 1;