INSERT INTO category (title)
VALUES ('Projekt');
INSERT INTO category (title)
VALUES ('IT-Support');

INSERT INTO user (password, username, role)
VALUES ('Testpwd12', 'test', 'user');
INSERT INTO user (password, username, role)
VALUES ('Adminpwd12', 'admin', 'admin');

INSERT INTO project (title, category_id)
VALUES ('punchclock1', 1);
INSERT INTO project (title, category_id)
VALUES ('punchclock2', 1);

INSERT INTO project (title, category_id)
VALUES ('Repair-Computer', 2);

INSERT INTO entry (checkin, checkout, project_id, user_id)
VALUES ('2021-09-22 13:21:58.737', '2021-09-22T13:22:58.737', 1, 1);
INSERT INTO entry (checkin, checkout, project_id, user_id)
VALUES ('2021-09-22 13:21:58.737', '2021-09-22T13:22:58.737', 1, 1);

INSERT INTO entry (checkin, checkout, project_id, user_id)
VALUES ('2021-09-22 13:21:58.737', '2021-09-22T13:22:58.737', 3, 1);


