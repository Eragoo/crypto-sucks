alter table transaction
    alter column from_amount type numeric using from_amount::numeric;

alter table transaction
    alter column to_amount type numeric using to_amount::numeric;

alter table transaction
    alter column usd_amount type numeric using usd_amount::numeric;
