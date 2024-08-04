--liquibase formatted sql

--changeset create_stocks:1
INSERT INTO stock(id,extid,symbol,name,ready,created_at,modified_at,deleted_at,active)VALUES(1,"extid1","symbol","name1",1,"2024-08-01 11:04:57",null,null,1);