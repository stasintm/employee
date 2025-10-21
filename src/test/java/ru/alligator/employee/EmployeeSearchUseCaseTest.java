package ru.alligator.employee;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.jdbc.Sql;
import ru.alligator.employee.repo.EmployeeRepository;
import ru.alligator.employee.service.EmployeeSearchUseCase;

@Sql(
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
    scripts = "classpath:/sql/employee.sql"
)
public class EmployeeSearchUseCaseTest extends EmployeeApplicationTests {
    @Autowired
    private EmployeeSearchUseCase useCase;
    @Autowired
    private EmployeeRepository repo;
    @LocalServerPort
    private Integer port;

    @Test
    void testSearch() {
        assertEquals(1, useCase.execute("Иванов").size());
        assertEquals(0, useCase.execute("Сидоров").size());
        assertEquals(1, useCase.execute("petrov@alligator.ru").size());
        assertEquals(1, useCase.execute("79201234567").size());
        assertEquals(1, useCase.execute("+7 (920) 123-45-67").size());
        assertEquals(0, useCase.execute("79201234560").size());
    }

    @Description("Тест контроллера")
    @Test
    void testController() {
        RestAssured.port = port;
        given()
            .queryParam("query", "79201234567")
            .when()
            .get("api/v1/employees")
            .then()
            .statusCode(200)
            .body("size()", equalTo(1))
            .body("[0].lastName", equalTo("Иванов"));
    }

    @AfterEach
    void cleanup() {
        repo.deleteAll();
    }
}
