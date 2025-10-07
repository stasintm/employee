package ru.alligator.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.alligator.employee.domain.Employee;
import ru.alligator.employee.domain.QEmployee;

import java.util.UUID;

/**
 * JPA репозиторий сотрудника.
 */
public interface EmployeeRepository extends JpaRepository<Employee, UUID>,
    QuerydslPredicateExecutor<QEmployee> {
}
