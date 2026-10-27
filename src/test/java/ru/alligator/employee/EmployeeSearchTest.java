package ru.alligator.employee;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import ru.alligator.employee.repository.EmployeeRepository;
import ru.alligator.employee.service.EmployeeSearchUseCase;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(scripts = "classpath:sql/employee.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EmployeeSearchTest extends IntegrationTestBase {

    @Autowired
    private EmployeeSearchUseCase employeeSearchUseCase;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

	@Test
	void testSearch() {
        assertEquals(1, employeeSearchUseCase.search("Иванов").size());
        assertEquals(1, employeeSearchUseCase.search("petrov@alligator.ru").size());
        assertEquals(1, employeeSearchUseCase.search("+7 (920) 123-45-67").size());
        assertEquals(0, employeeSearchUseCase.search("Сидоров").size());
        assertEquals(1, employeeSearchUseCase.search("Петров Пет").size());
	}

    @Test
    void testApi() {
        RestAssured.port = port;
        RestAssured
                .given().queryParam("query", "Иванов")
                .when().get("/api/v1/employees")
                .then().statusCode(200)
                .body("size()", equalTo(1));
                //.body("[0].lastname", equalTo("Иванов"));
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }


}
