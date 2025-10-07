package ru.alligator.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alligator.employee.domain.Employee;
import ru.alligator.employee.dto.EmployeeTo;
import ru.alligator.employee.mapper.EmployeeMapper;
import ru.alligator.employee.repo.DepartmentRepository;
import ru.alligator.employee.repo.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис работы с сотрудниками.
 */
@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    private final EmployeeMapper mapper;
    private final DepartmentRepository departmentRepo;

    /**
     * Поиск сотрудника по id.
     * @param id id сотрудника
     * @return  to сотрудника
     */
    @Transactional(readOnly = true)
    public EmployeeTo getById(UUID id) {
        return mapper.toTo(repo.findById(id).orElse(null));
    }

    /**
     * Поиск  всех сотрудников.
     * @return  список to сотрудников
     */
    @Transactional(readOnly = true)
    public List<EmployeeTo> findAll() {
        return repo.findAll().stream().map(mapper::toTo).collect(Collectors.toList());
    }

    /**
     * Создание нового сотрудника.
     * @param to to сотрудника
     * @return to созданного сотрудника
     */
    @Transactional
    public EmployeeTo create(EmployeeTo to) {
        var employee = new Employee();
        employee.setId(UUID.randomUUID());
        toToEntity(employee, to);
        return mapper.toTo(repo.save(employee));
    }

    /**
     * Изменение сотрудника.
     * @param to to сотрудника
     * @return to изменененного сотрудника
     */
    @Transactional
    public EmployeeTo update(EmployeeTo to) {
        var employee = repo.findById(to.getId()).orElseThrow(IllegalArgumentException::new);
        toToEntity(employee, to);
        return mapper.toTo(repo.save(employee));
    }

    /**
     * Удаление сотрудника.
     * @param id id сотрудника
     */
    @Transactional
    public void delete(UUID id) {
        repo.findById(id).ifPresent(employee -> {
            employee.setDeleted(true);
            repo.save(employee);
        });
    }

    private void toToEntity(Employee employee, EmployeeTo to) {
        employee.setFirstName(to.getFirstName());
        employee.setLastName(to.getLastName());
        employee.setPatronymic(to.getPatronymic());
        employee.setEmail(to.getEmail());
        employee.setPhone(to.getPhone());
        employee.setPosition(to.getPosition());
        employee.setDepartment(Optional.ofNullable(to.getDepartmentId())
            .flatMap(departmentRepo::findById).orElse(null));
    }
}
