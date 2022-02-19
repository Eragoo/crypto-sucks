create table favorite
(
    id      bigint not null
        constraint favorite_pk
            primary key,
    user_id bigint not null
        constraint favorite_user_id_fk
            references "user",
    coin_id bigint not null
        constraint favorite_coin_id_fk
            references coin
);

create unique index favorite_id_uindex
    on favorite (id);

create unique index favorite_user_id_coin_id_uindex
    on favorite (user_id, coin_id);

alter table favorite
    owner to core;

create sequence favorite_sequence
    increment by 50;

alter sequence favorite_sequence owner to core;
