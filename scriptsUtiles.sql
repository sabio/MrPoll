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

