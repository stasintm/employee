package ru.alligator.employee.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alligator.employee.dto.EmployeeTo;
import ru.alligator.employee.service.EmployeeSearchUseCase;
import ru.alligator.employee.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final EmployeeSearchUseCase employeeSearchUseCase;

    @PostMapping
    public EmployeeTo createEmployee(@RequestBody EmployeeTo to) {
        return employeeService.create(to);
    }

    @PutMapping("/{id}")
    public EmployeeTo updateEmployee(@PathVariable UUID id, @RequestBody EmployeeTo to) {
        to.setId(id);
        return employeeService.update(to);
    }

    @Operation(summary = "Получить сотрудника по id")
    @GetMapping("/{id}")
    public EmployeeTo getEmployee(@PathVariable UUID id) {
        return employeeService.getById(id);
    }

    @GetMapping("/all")
    public List<EmployeeTo> getAll() {
        return employeeService.findAll();
    }

    @GetMapping
    public List<EmployeeTo> findEmployees(String query) {
        return employeeSearchUseCase.search(query);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.delete(id);
    }
}
