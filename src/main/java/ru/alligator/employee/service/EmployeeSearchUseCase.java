package ru.alligator.employee.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.alligator.employee.domain.Employee;
import ru.alligator.employee.domain.QEmployee;
import ru.alligator.employee.dto.EmployeeTo;
import ru.alligator.employee.mapper.EmployeeMapper;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * UseCase поиска сотрудников по параметрам.
 */
@Component
@RequiredArgsConstructor
public class EmployeeSearchUseCase {

    private static final String FULL_NAME_PATTERN = "^(?:[А-Яа-я]{3,16})(?: [А-Яа-я]{3,16}){0,2}$";

    private static final String PHONE_PATTERN = "(^8|7|\\+7)((\\d{10})|[\\s-][(]\\d{3}[)][\\s-]\\d{3}[\\s-]\\d{2}[\\s-]\\d{2})";

    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private final EntityManager entityManager;
    private final EmployeeMapper mapper;

    /**
     * Метод поиска сотрудников по параметрам.
     * @param query поисковая строка
     * @return      список сотрудников
     */
    public List<EmployeeTo> execute(String query) {
        JPAQuery<Employee> jpaQuery = new JPAQuery<>(entityManager);
        return jpaQuery.select(QEmployee.employee).from(QEmployee.employee)
            .where(createPredicate(query)).limit(10)
            .fetch().stream().map(mapper::toTo).collect(Collectors.toList());
    }

    private BooleanBuilder createPredicate(String query) {
        BooleanBuilder condition = new BooleanBuilder();
        if (Pattern.matches(FULL_NAME_PATTERN, query)) {
            return condition.and(QEmployee.employee.lastName
                .concat(StringUtils.SPACE)
                .concat(QEmployee.employee.firstName)
                .concat(StringUtils.SPACE)
                .concat(QEmployee.employee.patronymic.coalesce(StringUtils.EMPTY))
                .containsIgnoreCase(query)
            );
        } else if (Pattern.matches(PHONE_PATTERN, query)) {
            var phone = query.replaceAll("\\s", "")
                .replaceAll("[\\(\\)\\+-]", "");
            return condition.and(QEmployee.employee.phone.eq(phone));
        } else if (Pattern.matches(EMAIL_PATTERN, query)) {
            return condition.and(QEmployee.employee.email.eq(query));
        } else {
            throw new IllegalArgumentException("incorrect query string!");
        }
    }
}
