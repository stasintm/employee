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

@Entity
@Data
public class Employee {
    @Id
    private UUID id;

    @Column(nullable = false, length = 64)
    private String lastName;

    @Column(nullable = false, length = 64)
    private String firstName;

    @Column(length = 64)
    private String patronymic;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(length = 32)
    private String email;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Boolean deleted;

    @Version
    private Long ts;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;



}
