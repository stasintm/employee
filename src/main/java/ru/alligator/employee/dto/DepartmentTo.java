package ru.alligator.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentTo {
    private UUID id;

    private String name;

    private UUID higherDepartmentId;
}
