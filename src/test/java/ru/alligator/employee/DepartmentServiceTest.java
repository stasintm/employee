package ru.alligator.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alligator.employee.dto.DepartmentTo;
import ru.alligator.employee.repository.DepartmentRepository;
import ru.alligator.employee.service.DepartmentService;

public class DepartmentServiceTest extends EmployeeApplicationTests {

    @Autowired
    private DepartmentRepository repo;

    @Autowired
    private DepartmentService service;

    @BeforeEach
    void startup() {
    }

    @AfterEach
    void cleanup() {
        repo.deleteAll();
    }

    @Test
    void testService() {
        var to = new DepartmentTo();
        to.setName("IT");
        to = service.create(to);
        assertTrue(repo.findById(to.getId()).isPresent());
        to.setName("IT Department");
        to = service.update(to);
        assertEquals("IT Department", repo.findById(to.getId()).get().getName());
        var parentId = to.getId();
        to = new DepartmentTo();
        to.setName("R&D");
        to.setParentId(parentId);
        to = service.create(to);
        service.delete(to.getId());
        assertEquals(2, repo.findAll().size());
        var dep = repo.findById(to.getId()).get();
        assertNotNull(dep.getParent()                                                                                                                                                                                                                                                                                                                                           );
        assertTrue(dep.getDeleted());
    }
}
