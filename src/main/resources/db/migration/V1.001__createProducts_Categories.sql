create sequence hibernate_sequence start 1 increment 1;
create table category (name varchar(255) not null, primary key (name));
create table product (id int8 not null, name varchar(255), price int8, category_name varchar(255), primary key (id));
alter table if exists product add constraint FK_PRODUCT_TO_CATEGORY foreign key (category_name) references category;
