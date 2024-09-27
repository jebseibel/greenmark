--liquibase formatted sql

--changeset create_positions:3
INSERT INTO position VALUES(1,"6e074f41-b1ca-431d-aae2-cf8dfb57cb41","GME","Init GME",10,21.12, 212.10, now(),null,null,1);
