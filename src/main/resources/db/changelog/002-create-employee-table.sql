--liquibase formatted sql

--changeset alligator:002-create-employee-table
CREATE TABLE employee
(
    id            UUID         NOT NULL,
    last_name     VARCHAR(255),
    first_name    VARCHAR(255),
    patronymic    VARCHAR(255),
    phone         VARCHAR(50),
    email         VARCHAR(255),
    position      VARCHAR(50),
    manager_id    UUID,
    department_id UUID,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

ALTER TABLE employee
    ADD CONSTRAINT fk_employee_manager
        FOREIGN KEY (manager_id) REFERENCES employee (id);

ALTER TABLE employee
    ADD CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id) REFERENCES department (id);