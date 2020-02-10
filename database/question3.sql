-- What books and movies aren't checked out?
SELECT(
    (SELECT count(mo.id) FROM movie mo LEFT JOIN checkout_item ci ON mo.id = ci.movie_id WHERE ci.member_id IS NULL) +
    (SELECT count(bo.id) FROM book bo LEFT JOIN checkout_item ci ON bo.id = ci.movie_id WHERE ci.member_id IS NULL)
) AS 'Books and movies not checked out';
