package ru.alligator.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alligator.employee.model.Department;

import java.util.UUID;

/**
 * Репозиторий для работы с отделами организации.
 * <p>
 * Предоставляет стандартные CRUD операции для сущности {@link Department}.
 * Расширяет JpaRepository для автоматической реализации базовых методов работы с базой данных.
 * </p>
 */
public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
