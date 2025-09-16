package ru.alligator.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alligator.employee.domain.Department;
import ru.alligator.employee.dto.DepartmentTo;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "higherDepartmentId", source = "department.higherDepartment.id")
    DepartmentTo toTo(Department department);
}
