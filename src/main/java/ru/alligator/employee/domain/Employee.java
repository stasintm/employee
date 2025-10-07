package ru.alligator.employee.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Data;

import java.util.UUID;

/**
 * Сотрудник.
 */
@Entity
@Data
public class Employee {

    /**
     * id.
     */
    @Id
    private UUID id;

    /**
     * Фамилия.
     */
    @Column(nullable = false, length = 64)
    private String lastName;

    /**
     * Имя.
     */
    @Column(nullable = false, length = 64)
    private String firstName;

    /**
     * Отчество.
     */
    @Column(length = 64)
    private String patronymic;

    /**
     * Номер телефона.
     */
    @Column(nullable = false, length = 11)
    private String phone;

    /**
     * email.
     */
    @Column(length = 32)
    private String email;

    /**
     * Должность.
     */
    @Enumerated(EnumType.STRING)
    private Position position;

    /**
     * Признак удаления.
     */
    private Boolean deleted;

    /**
     * Версия.
     */
    @Version
    private Long ts;

    /**
     * Вышестоящий менеджер.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee manager;

    /**
     * Подразделение сотрудника.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
}
