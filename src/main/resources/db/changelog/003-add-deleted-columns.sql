--liquibase formatted sql

--changeset alligator:003-add-deleted-columns
ALTER TABLE employee ADD COLUMN deleted BOOLEAN;
ALTER TABLE department ADD COLUMN deleted BOOLEAN;