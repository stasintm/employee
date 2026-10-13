--liquibase formatted sql

--changeset alligator:001-create-department-table
CREATE TABLE department
(
    id        UUID         NOT NULL,
    name      VARCHAR(255),
    parent_id UUID,
    CONSTRAINT pk_department PRIMARY KEY (id)
);

ALTER TABLE department
    ADD CONSTRAINT fk_department_parent
        FOREIGN KEY (parent_id) REFERENCES department (id);
