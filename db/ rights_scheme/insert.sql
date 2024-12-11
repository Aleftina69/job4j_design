insert into roles(name) values('Moderator');
insert into users(name, roles_id) values('Pavel', 1);
insert into rules(name) values('limited access');
insert into roles_rules(roles_id, rules_id) values(1,1);
insert into categories(name) values('internet');
insert into states(name) values('waiting');
insert into items(name, users_id, categories_id, states_id) values('server', 1, 1, 1);
insert into comments (name, items_id) values ('in processing', 1);
insert into attachs(name, items_id) values ('photo.jpg', 1);