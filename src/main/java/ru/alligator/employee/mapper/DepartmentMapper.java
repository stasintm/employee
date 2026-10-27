package ru.alligator.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alligator.employee.dto.DepartmentTo;
import ru.alligator.employee.model.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "parentId", source = "department.parent.id")
    DepartmentTo toTo(Department department);

}
