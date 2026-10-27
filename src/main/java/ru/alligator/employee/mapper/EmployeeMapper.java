package ru.alligator.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alligator.employee.dto.EmployeeTo;
import ru.alligator.employee.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "departmentId", source = "employee.department.id")
    @Mapping(target = "departmentName", source = "employee.department.name")
    @Mapping(target = "positionName", source = "employee.position.russianName")
    @Mapping(target = "managerId", source = "employee.manager.id")
    EmployeeTo toTo(Employee employee);

}
