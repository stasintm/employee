package ru.alligator.employee.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Data;

import java.util.UUID;

/**
 * Подразделение.
 */
@Entity
@Data
public class Department {

    /**
     * id.
     */
    @Id
    private UUID id;

    /**
     * Наименование подразделения.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Признак удаления.
     */
    private Boolean deleted;

    /**
     * Версия записи.
     */
    @Version
    private Long ts;

    /**
     * Вышестоящее подразделение.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Department higherDepartment;
}
