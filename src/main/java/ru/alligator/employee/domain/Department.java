package ru.alligator.employee.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Department {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    private Boolean deleted;

    @Version
    private Long ts;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department higherDepartment;
}
