create table "user"
(
    id             bigint not null
        primary key,
    email          varchar(255),
    password       varchar(255),
    username       varchar(255) unique not null,
    user_auth_type varchar(255) not null
);

alter table "user"
    owner to core;

create table coin
(
    id                              bigint not null
        primary key,
    circulating_supply              bigint,
    coin_gecko_id                   varchar(255) not null unique,
    current_price                   double precision,
    fully_diluted_valuation         bigint,
    high24h                         double precision,
    image                           varchar(255),
    low24h                          double precision,
    market_cap                      varchar(255),
    market_cap_change24h            double precision,
    market_cap_change_percentage24h double precision,
    market_cap_rank                 integer,
    max_supply                      bigint,
    name                            varchar(255),
    price_change24h                 double precision,
    price_change_percentage24h      double precision,
    symbol                          varchar(255),
    total_supply                    bigint,
    total_volume                    bigint
);

alter table coin
    owner to core;

create sequence coin_sequence
    increment by 50;

alter sequence coin_sequence owner to core;

create sequence user_sequence
    increment by 50;

alter sequence user_sequence owner to core;

