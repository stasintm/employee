package ru.alligator.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alligator.employee.domain.Employee;
import ru.alligator.employee.dto.EmployeeTo;

/**
 * Маппер сотрудника.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    /**
     * Маппинг сущности сотрудника в to.
     * @param employee сущность сотрудника
     * @return to сотрудника
     */
    @Mapping(target = "positionName", source = "employee.position.russianName")
    @Mapping(target = "departmentName", source = "employee.department.name")
    @Mapping(target = "departmentId", source = "employee.department.id")
    EmployeeTo toTo(Employee employee);
}
