package ru.alligator.employee.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alligator.employee.dto.DepartmentTo;
import ru.alligator.employee.service.DepartmentService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/{id}")
    public DepartmentTo findById(@PathVariable UUID id) {
        return departmentService.getById(id);
    }

    @GetMapping
    public List<DepartmentTo> findAll() {
        return departmentService.findAll();
    }

    @PostMapping
    public DepartmentTo createDepartment(@RequestBody DepartmentTo to) {
        return departmentService.create(to);
    }

    @PutMapping
    public DepartmentTo updateDepartment(@RequestBody DepartmentTo to) {
        return departmentService.update(to);
    }
}
