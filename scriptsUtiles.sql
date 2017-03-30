/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  armando
 * Created: 5/02/2017
 */


insert into user
(id, name, username, password, email, enabled)
values
(1,'armando','armando','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'aaaa@aaaa.com', true);

insert into role
(id, role)
values
(1, 'ROLE_ADMIN');

insert into role
(id, role)
values
(2, 'ROLE_USER');

insert into user_role
(user_id, role_id)
values
(1,1);













SELECT 
count(*),
q.question_text,
c.choice_text,
cr.question_id,
cr.choice_id
FROM response r
INNER JOIN choice_response cr on r.id = cr.response_id
INNER JOIN question q on q.id = cr.question_id
INNER JOIN choice c on c.id = cr.choice_id
WHERE
r.poll_id = 5
group by question_id, choice_id
;
