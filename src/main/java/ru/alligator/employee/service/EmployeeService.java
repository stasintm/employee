package ru.alligator.employee.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alligator.employee.dto.EmployeeTo;
import ru.alligator.employee.mapper.EmployeeMapper;
import ru.alligator.employee.model.Employee;
import ru.alligator.employee.repository.DepartmentRepository;
import ru.alligator.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public EmployeeTo create(EmployeeTo to) {
        Employee employee = new Employee();
        mapToToEntity(employee, to);
        employeeRepository.save(employee);
        return employeeMapper.toTo(employee);
    }

    @Transactional
    public EmployeeTo update(EmployeeTo to) {
        Employee employee = employeeRepository.findById(to.getId()).orElseThrow();
        mapToToEntity(employee, to);
        employeeRepository.save(employee);
        return employeeMapper.toTo(employee);
    }

    @Transactional(readOnly = true)
    public EmployeeTo getById(UUID id) {
        log.debug("Starting getById({})", id);
        return employeeMapper.toTo(employeeRepository.findById(id).orElseThrow());
    }

    @Transactional(readOnly = true)
    public List<EmployeeTo> findAll() {
        return employeeRepository.findAll().stream().map(employeeMapper::toTo).toList();
    }

    @Transactional
    public void delete(UUID id) {
        employeeRepository.findById(id).ifPresent(employee -> {
            employee.setDeleted(true);
            employeeRepository.save(employee);
        });
    }

    private void mapToToEntity(Employee entity, EmployeeTo to) {
        entity.setId(to.getId());
        entity.setFirstName(to.getFirstName());
        entity.setLastName(to.getLastName());
        entity.setPatronymic(to.getPatronymic());
        entity.setPhone(to.getPhone());
        entity.setEmail(to.getEmail());
        entity.setPosition(to.getPosition());
        entity.setDepartment(Optional.ofNullable(to.getDepartmentId())
                        .flatMap(departmentRepository::findById).orElse(null));
    }



}
