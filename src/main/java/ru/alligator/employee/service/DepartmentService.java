package ru.alligator.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alligator.employee.dto.DepartmentTo;
import ru.alligator.employee.mapper.DepartmentMapper;
import ru.alligator.employee.model.Department;
import ru.alligator.employee.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentRepository repo;
    private final DepartmentMapper mapper;

    @Transactional(readOnly = true)
    public DepartmentTo getById(UUID id) {
        return mapper.toTo(repo.findById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    public List<DepartmentTo> findAll() {
        return repo.findAll().stream().map(mapper::toTo).collect(Collectors.toList());
    }

    @Transactional
    public DepartmentTo create(DepartmentTo to) {
        var department = new Department();
        toToEntity(department, to);
        return mapper.toTo(repo.save(department));
    }

    @Transactional
    public DepartmentTo update(DepartmentTo to) {
        var department = repo.findById(to.getId()).orElseThrow(IllegalArgumentException::new);
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

    private void toToEntity(Department department, DepartmentTo to) {
        department.setName(to.getName());
        department.setParent(Optional.ofNullable(to.getParentId())
            .flatMap(repo::findById).orElse(null));
    }
}
