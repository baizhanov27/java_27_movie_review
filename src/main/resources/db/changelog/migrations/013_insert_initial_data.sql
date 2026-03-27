-- Authorities
insert into authorities (authority)
values ('MAKE'),
       ('EDIT'),
       ('REMOVE'),
       ('READ_ONLY');

-- Roles
insert into roles(role)
values ('ADMIN'),
       ('USER'),
       ('GUEST');

-- Users
insert into usr(email, username, password, enabled)
VALUES ('qwe@qwe.qwe',
        'qwe',
        '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        TRUE),
       ('ewq@ewq.com',
        'ewq',
        '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        TRUE),
       ('asd@asd.com',
        'asd',
        '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        TRUE);

-- Role_auth
insert into role_auth (auth_id, role_id)
values ((select id from authorities where authority = 'READ_ONLY'),
        (select id from roles where role = 'ADMIN')),
       ((select id from authorities where authority = 'MAKE'),
        (select id from roles where role = 'ADMIN')),
       ((select id from authorities where authority = 'EDIT'),
        (select id from roles where role = 'ADMIN')),
       ((select id from authorities where authority = 'REMOVE'),
        (select id from roles where role = 'ADMIN')),
       ((select id from authorities where authority = 'READ_ONLY'),
        (select id from roles where role = 'USER')),
       ((select id from authorities where authority = 'MAKE'),
        (select id from roles where role = 'USER')),
       ((select id from authorities where authority = 'READ_ONLY'),
        (select id from roles where role = 'GUEST'));

insert into user_role(role_id, usr_email)
values ((select id from roles where role = 'ADMIN'),
        (select email from usr where email = 'qwe@qwe.qwe')),
       ((select id from roles where role = 'USER'),
        (select email from usr where email = 'ewq@ewq.com')),
       ((select id from roles where role = 'GUEST'),
        (select email from usr where email = 'asd@asd.com'));
