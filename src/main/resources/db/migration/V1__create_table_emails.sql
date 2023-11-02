create table emails (
    id bigint generated always as identity,
    created_at timestamp(6) not null,
    deleted boolean not null,
    deleted_at timestamp(6),
    email_from varchar(255) not null,
    email_to varchar(255) not null,
    owner varchar(255) not null,
    status smallint check (status between 0 and 1)  not null,
    subject varchar(255) not null,
    text TEXT not null,
    primary key (id)
);