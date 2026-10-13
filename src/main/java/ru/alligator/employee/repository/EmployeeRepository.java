package ru.alligator.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.alligator.employee.model.Employee;

import java.util.UUID;

/**
 * Репозиторий для работы с сотрудниками организации.
 * <p>
 * Предоставляет стандартные CRUD операции для сущности {@link Employee},
 * а также поддержку динамических запросов через Querydsl.
 * </p>
 */
public interface EmployeeRepository extends JpaRepository<Employee, UUID>,
        QuerydslPredicateExecutor<Employee>{
}
