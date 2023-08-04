insert into user_details(id, birth_date, name)
values(10001, current_date(), 'Rishu');

insert into user_details(id, birth_date, name)
values(10002, current_date(), 'Prince');

insert into user_details(id, birth_date, name)
values(10003, current_date(), 'Ram');

insert into post(id, description, user_id)
values(20001, 'I want to learn spring boot', 10001);

insert into post(id, description, user_id)
values(20002, 'I want to learn AWS', 10002);

insert into post(id, description, user_id)
values(20003, 'I want to learn Devops', 10002);