create table category_aud (name varchar(255) not null, rev int4 not null, revtype int2, primary key (name, rev));
create table product_aud (id int8 not null, rev int4 not null, revtype int2, name varchar(255), price int8, category_name varchar(255), primary key (id, rev));
create table revinfo (rev int4 not null, revtstmp int8, primary key (rev));
alter table if exists category_aud add constraint FK_CATEGORY_REVINFO foreign key (rev) references revinfo;
alter table if exists product_aud add constraint FK_PRODUCT_REVINFO foreign key (rev) references revinfo;
