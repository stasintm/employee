package ru.alligator.employee.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO для передачи информации об отделе.
 * <p>
 * Используется для передачи данных об отделе между слоями приложения.
 * </p>
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DepartmentTo {
    /**
     * Уникальный идентификатор отдела.
     */
    private UUID id;

    /**
     * Название отдела.
     */
    private String name;

    /**
     * Идентификатор родительского отдела.
     */
    private UUID parentId;
}
