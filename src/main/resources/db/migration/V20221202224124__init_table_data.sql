-- init user table
insert into user(id, name, username, password)
values (1, 'admin sally', 'admin', '{bcrypt}$2a$10$a486JBcw6PrJV6KDhEBZYOEoAi2LQv44.hW.N33wQ5FUYYVkCob.S');
insert into user(id, name, username, password)
values (2, 'read user', 'readUser', '{bcrypt}$2a$10$a486JBcw6PrJV6KDhEBZYOEoAi2LQv44.hW.N33wQ5FUYYVkCob.S');
insert into user(id, name, username, password)
values (3, 'edit user', 'editUser', '{bcrypt}$2a$10$a486JBcw6PrJV6KDhEBZYOEoAi2LQv44.hW.N33wQ5FUYYVkCob.S');
insert into user(id, name, username, password)
values (4, 'delete user', 'deleteUser', '{bcrypt}$2a$10$a486JBcw6PrJV6KDhEBZYOEoAi2LQv44.hW.N33wQ5FUYYVkCob.S');
-- init role table
insert into role(id, name)
values (1, 'admin'),
       (2, 'read'),
       (3, 'edit'),
       (4, 'delete');
-- init user role table
insert into user_role(user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);
-- init permission table
insert into permission(id, name, label)
values (1, 'read', 'read'),
       (2, 'edit', 'edit'),
       (3, 'delete', 'delete');
-- init role permission table
insert into role_permission(permission_id, role_id)
values (1, 1),(2, 1),(3, 1),(1, 2),(2, 3),(3, 4);

