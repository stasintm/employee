package ru.alligator.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alligator.employee.domain.Department;
import ru.alligator.employee.dto.DepartmentTo;
import ru.alligator.employee.mapper.DepartmentMapper;
import ru.alligator.employee.repo.DepartmentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository repo;
    private final DepartmentMapper mapper;

    private void toToEntity(Department department, DepartmentTo departmentTo) {
        department.setName(departmentTo.getName());
        department.setHigherDepartment(Optional.ofNullable(departmentTo.getHigherDepartmentId())
            .flatMap(repo::findById).orElse(null));
    }

    @Transactional
    public DepartmentTo create(DepartmentTo to) {
        Department department = new Department();
        department.setId(UUID.randomUUID());
        toToEntity(department, to);
        return mapper.toTo(repo.save(department));
    }

    @Transactional
    public DepartmentTo update(DepartmentTo to) {
        Department department = repo.findById(to.getId()).orElseThrow(IllegalArgumentException::new);
        toToEntity(department, to);
        return mapper.toTo(repo.save(department));
    }

    @Transactional
    public void delete(UUID id) {
       repo.findById(id).ifPresent(department -> {
           department.setDeleted(true);
           repo.save(department);
       });
    }

    @Transactional(readOnly = true)
    public DepartmentTo getById(UUID id) {
        return mapper.toTo(repo.findById(id).orElseThrow(IllegalArgumentException::new));
    }
}
