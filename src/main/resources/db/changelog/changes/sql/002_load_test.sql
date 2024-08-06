--liquibase formatted sql

--changeset create_spaz:1
insert into spaz (id, extid, symbol, name) values (1, 'extid1', 'symbol1', 'name1');
insert into spaz (id, extid, symbol, name) values (2, 'extid2', 'symbol2', 'name2');