package ru.alligator.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alligator.employee.domain.Department;
import ru.alligator.employee.dto.DepartmentTo;
import ru.alligator.employee.mapper.DepartmentMapper;
import ru.alligator.employee.repo.DepartmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис работы с подразделениями.
 */
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

    /**
     * Создание подразделения.
     * @param to to подразделения
     * @return to созданного подразделения
     */
    @Transactional
    public DepartmentTo create(DepartmentTo to) {
        Department department = new Department();
        department.setId(UUID.randomUUID());
        toToEntity(department, to);
        return mapper.toTo(repo.save(department));
    }

    /**
     * Изменение подразделения.
     * @param to to подразделения
     * @return to изменение подразделения
     */
    @Transactional
    public DepartmentTo update(DepartmentTo to) {
        Department department = repo.findById(to.getId()).orElseThrow(IllegalArgumentException::new);
        toToEntity(department, to);
        return mapper.toTo(repo.save(department));
    }

    /**
     * Удаление подразделения.
     * @param id id подразделения
     */
    @Transactional
    public void delete(UUID id) {
       repo.findById(id).ifPresent(department -> {
           department.setDeleted(true);
           repo.save(department);
       });
    }

    /**
     * Поиск подразделения по id.
     * @param id id подразделения
     * @return  to подразделения
     */
    @Transactional(readOnly = true)
    public DepartmentTo getById(UUID id) {
        return mapper.toTo(repo.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    /**
     * Поиск  всех подразделений.
     * @return  список to подразделений
     */
    @Transactional(readOnly = true)
    public List<DepartmentTo> findAll() {
        return repo.findAll().stream().map(mapper::toTo).collect(Collectors.toList());
    }
}
