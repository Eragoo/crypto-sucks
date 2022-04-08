drop table wallet_coin;

create table transaction
(
    id          bigint       not null
        constraint transaction_pk
            primary key,
    from_id     bigint references coin(id),
    to_id       bigint references coin(id),
    wallet_id   bigint       not null references wallet(id),
    from_amount double precision,
    to_amount   double precision,
    usd_amount  double precision,
    type        varchar(255) not null,
    comment     text
);

create unique index transaction_id_uindex
    on transaction (id);




