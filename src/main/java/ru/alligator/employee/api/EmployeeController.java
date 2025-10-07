package ru.alligator.employee.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alligator.employee.dto.DepartmentTo;
import ru.alligator.employee.dto.EmployeeTo;
import ru.alligator.employee.service.DepartmentService;
import ru.alligator.employee.service.EmployeeSearchUseCase;
import ru.alligator.employee.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeSearchUseCase searchUseCase;

    @GetMapping("/{id}")
    public EmployeeTo findById(@PathVariable UUID id) {
        return employeeService.getById(id);
    }

    @GetMapping
    public List<EmployeeTo> findByFilter(@RequestParam String query) {
        return searchUseCase.execute(query);
    }

    @PostMapping
    public EmployeeTo createEmployee(@RequestBody EmployeeTo to) {
        return employeeService.create(to);
    }

    @PutMapping
    public EmployeeTo updateEmployee(@RequestBody EmployeeTo to) {
        return employeeService.update(to);
    }
}
