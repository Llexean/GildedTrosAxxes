drop table T_ITEM if exists;

create table T_ITEM (ID integer identity primary key, NAME varchar(100) not null, SELL_IN integer not null, QUALITY integer not null, ITEM_TYPE varchar(20) not null);