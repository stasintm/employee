package ru.alligator.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.UUID)
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

    private Boolean deleted = false;
}
