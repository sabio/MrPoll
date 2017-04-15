insert into role
(id, role)
values
(1, 'ROLE_ADMIN');

insert into role
(id, role)
values
(2, 'ROLE_USER');

insert into user
(id, name, username, password, email, enabled)
values
(1,'root','root','$2a$10$EkKBWl7zF6O1rKMj7JyzE.hwJvU1w8VxIK..d5XFW5avMsSYlT5wq', 'root@aaaa.com', true);

insert into user_role
(user_id, role_id)
values
(1,1);


insert into user
(id, name, username, password, email, enabled)
values
(2,'regularuser','regularuser','$2a$10$EkKBWl7zF6O1rKMj7JyzE.hwJvU1w8VxIK..d5XFW5avMsSYlT5wq', 'regularuser@aaaa.com', true);

insert into user_role
(user_id, role_id)
values
(2,2);

