package ru.alligator.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alligator.employee.domain.Department;

import java.util.UUID;

/**
 * JPA репозиторий подразделения.
 */
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
