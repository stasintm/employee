package ru.alligator.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alligator.employee.domain.Department;
import ru.alligator.employee.dto.DepartmentTo;

/**
 * Маппер подразделения.
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    /**
     * Маппинг сущности подразделения в to.
     * @param department сущность подразделения
     * @return to подразделения
     */
    @Mapping(target = "higherDepartmentId", source = "department.higherDepartment.id")
    DepartmentTo toTo(Department department);
}
