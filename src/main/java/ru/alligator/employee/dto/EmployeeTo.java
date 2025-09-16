package ru.alligator.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alligator.employee.domain.Position;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeTo {

    private UUID id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private String phone;

    private String email;

    private Position position;

    private String positionName;

    private UUID departmentId;

    private String departmentName;
}
