package ru.alligator.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * To подразделения.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentTo {

    /**
     * id
     */
    private UUID id;

    /**
     * Наименование подразделения.
     */
    private String name;

    /**
     * id вышестоящего подразделения.
     */
    private UUID higherDepartmentId;
}
