package ru.alligator.employee;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    static PostgreSQLContainer POSTGRESQL_CONTAINER = new PostgreSQLContainer("postgres:15")
            .withPassword("postgres")
            .withUsername("postgres")
            .withDatabaseName("employee");

    static {
        POSTGRESQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
    }

}
