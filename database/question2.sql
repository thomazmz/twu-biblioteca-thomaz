-- How many people have not checked out anything?
SELECT count(mo.id) AS 'People that have not checked out anything'
FROM member mo
LEFT JOIN checkout_item ci
    ON mo.id = ci.member_id
WHERE ci.member_id IS NULL;