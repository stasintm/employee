package ru.alligator.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alligator.employee.dto.DepartmentTo;
import ru.alligator.employee.repo.DepartmentRepository;
import ru.alligator.employee.service.DepartmentService;

public class DepartmentServiceTest extends EmployeeApplicationTests {

    @Autowired
    private DepartmentService service;

    @Autowired
    private DepartmentRepository repository;

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    void testServiceCrudOperations() {
        var to = new DepartmentTo();
        to.setName("IT");
        to = service.create(to);
        assertTrue(repository.findById(to.getId()).isPresent());
        assertEquals(1, repository.count());
        to.setName("IT Department");
        to = service.update(to);
        assertEquals("IT Department", repository.findById(to.getId()).get().getName());
        var higherId = to.getId();
        to = new DepartmentTo();
        to.setName("R&D");
        to.setHigherDepartmentId(higherId);
        to = service.create(to);
        service.delete(to.getId());
        assertEquals(2, repository.count());
        var dep = repository.findById(to.getId()).get();
        assertNotNull(dep.getHigherDepartment());
        assertTrue(dep.getDeleted());
    }

}
