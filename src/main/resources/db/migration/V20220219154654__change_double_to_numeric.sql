alter table coin
    alter column current_price type numeric using current_price::numeric;

alter table wallet_coin
    alter column amount type numeric using amount::numeric;

