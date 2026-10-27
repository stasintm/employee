package ru.alligator.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Сущность сотрудника организации.
 */
@Entity
@Getter
@Setter
public class Employee {
    /**
     * Уникальный идентификатор сотрудника.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    @Enumerated(EnumType.STRING)
    private Position position;

    /**
     * Непосредственный руководитель сотрудника.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    /**
     * Отдел, в котором работает сотрудник.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    private Boolean deleted = false;

}
