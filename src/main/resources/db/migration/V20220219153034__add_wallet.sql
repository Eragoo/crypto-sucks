create table wallet
(
    id       bigint       not null
        constraint wallet_pk
            primary key,
    name     varchar(255) not null,
    owner_id bigint       not null
        constraint wallet_user_id_fk
            references "user"
);

create unique index wallet_id_uindex
    on wallet (id);

create sequence wallet_sequence
    increment by 50;

alter sequence wallet_sequence owner to core;

alter table wallet owner to core;

create table wallet_coin
(
    id        bigint           not null
        constraint wallet_coin_pk
            primary key,
    coin_id   bigint           not null
        constraint wallet_coin_coin_id_fk
            references coin,
    wallet_id bigint           not null
        constraint wallet_coin_wallet_id_fk
            references wallet (id),
    amount    double precision not null
);

create unique index wallet_coin_coin_id_wallet_id_uindex
    on wallet_coin (coin_id, wallet_id);

create unique index wallet_coin_id_uindex
    on wallet_coin (id);

create sequence wallet_coin_sequence
    increment by 50;

alter sequence wallet_coin_sequence owner to core;

alter table wallet_coin owner to core;
