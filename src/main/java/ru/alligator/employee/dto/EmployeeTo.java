package ru.alligator.employee.dto;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import ru.alligator.employee.model.Position;

import java.util.UUID;

/**
 * DTO для передачи информации о сотруднике.
 * <p>
 * Используется для передачи данных о сотруднике между слоями приложения.
 * </p>
 */
@Data
@Builder
public class EmployeeTo {
    /**
     * Уникальный идентификатор сотрудника.
     */
    @Id
    private UUID id;

    /**
     * Фамилия сотрудника.
     */
    private String lastName;

    /**
     * Имя сотрудника.
     */
    private String firstName;

    /**
     * Отчество сотрудника.
     */
    private String patronymic;

    /**
     * Номер телефона сотрудника.
     */
    private String phone;

    /**
     * Адрес электронной почты сотрудника.
     */
    private String email;

    /**
     * Должность сотрудника.
     */
    private Position position;

    /**
     * Название должности сотрудника.
     */
    private String positionName;

    /**
     * Идентификатор руководителя сотрудника.
     */
    private UUID managerId;

    /**
     * Идентификатор отдела, в котором работает сотрудник.
     */
    private UUID departmentId;

    /**
     * Название отдела, в котором работает сотрудник.
     */
    private String departmentName;
}
