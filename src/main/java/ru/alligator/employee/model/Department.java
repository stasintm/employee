package ru.alligator.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Сущность отдела организации.
 */
@Entity
@Getter
@Setter
public class Department {
    /**
     * Уникальный идентификатор отдела.
     */
    @Id
    private UUID id;

    /**
     * Название отдела.
     */
    private String name;

    /**
     * Родительский отдел.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Department parent;
}
