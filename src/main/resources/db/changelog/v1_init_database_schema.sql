-- liquibase formatted sql

-- changeset Admin:1719839383701-1
CREATE TABLE department
(
    id                   UUID         NOT NULL,
    name                 VARCHAR(255) NOT NULL,
    deleted              BOOLEAN,
    ts                   BIGINT,
    higher_department_id UUID,
    CONSTRAINT pk_department PRIMARY KEY (id)
);

-- changeset Admin:1719839383701-2
CREATE TABLE employee
(
    id            UUID        NOT NULL,
    last_name     VARCHAR(64) NOT NULL,
    first_name    VARCHAR(64) NOT NULL,
    patronymic    VARCHAR(64),
    phone         VARCHAR(11) NOT NULL,
    email         VARCHAR(32),
    department_id UUID,
    position      VARCHAR(255),
    manager_id    UUID,
    deleted       BOOLEAN,
    ts            BIGINT,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

-- changeset Admin:1719839383701-3
ALTER TABLE department
    ADD CONSTRAINT FK_DEPARTMENT_ON_HIGHER_DEPARTMENT FOREIGN KEY (higher_department_id) REFERENCES department (id);

-- changeset Admin:1719839383701-4
ALTER TABLE employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES department (id);

-- changeset Admin:1719839383701-5
ALTER TABLE employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_MANAGER FOREIGN KEY (manager_id) REFERENCES employee (id);

