create table genres(
    id bigserial primary key,
    name text not null
    );

create table books(
    id bigserial primary key,
    title text not null,
    author text not null,
    pages int not null check ( pages > 0 ),
    quantity int not null check ( quantity >= 0 ),
    genre_id bigint not null references genres(id) on delete cascade,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
    );