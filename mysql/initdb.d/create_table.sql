create table if not exists dockerComposePractice.users
(
    user_id  bigint       not null,
    username varchar(255) not null,
    primary key (user_id),
    unique key (username)
);

create table dockerComposePractice.users_seq
(
    next_val bigint
);
