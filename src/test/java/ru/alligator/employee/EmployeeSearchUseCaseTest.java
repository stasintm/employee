package ru.alligator.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.alligator.employee.service.EmployeeSearchUseCase;

@Sql(
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
    scripts = "classpath:/sql/employee.sql"
)
public class EmployeeSearchUseCaseTest extends EmployeeApplicationTests {
    @Autowired
    private EmployeeSearchUseCase useCase;

    @Test
    void testSearch() {
        assertEquals(1, useCase.execute("Иванов").size());
        assertEquals(0, useCase.execute("Сидоров").size());
        assertEquals(1, useCase.execute("petrov@alligator.ru").size());
        assertEquals(1, useCase.execute("79201234567").size());
        assertEquals(0, useCase.execute("79201234560").size());
    }
}
