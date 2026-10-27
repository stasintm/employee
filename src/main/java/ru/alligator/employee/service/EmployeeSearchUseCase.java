package ru.alligator.employee.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alligator.employee.dto.EmployeeTo;
import ru.alligator.employee.mapper.EmployeeMapper;
import ru.alligator.employee.model.Employee;
import ru.alligator.employee.model.QEmployee;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EmployeeSearchUseCase {

    private static final Pattern FULL_NAME_PATTERN = Pattern.compile("^(?:[А-Яа-я]{2,})(?: [А-Яа-я]{2,}){0,2}$");

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "(^8|7|\\+7)((\\d{10})|[\\s-][(]\\d{3}[)][\\s-]\\d{3}[\\s-]\\d{2}[\\s-]\\d{2})");

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    private final EntityManager entityManager;
    private final EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public List<EmployeeTo> search(String queryStr) {
        JPAQuery<Employee> query = new JPAQuery<>(entityManager);
        return query.select(QEmployee.employee)
                .from(QEmployee.employee)
                .limit(10)
                .where(createPredicate(queryStr))
                .stream().map(employeeMapper::toTo).toList();
    }

    private Predicate createPredicate(String queryStr) {
        switch (queryStr) {
            case String s
                    when FULL_NAME_PATTERN.matcher(s).matches() -> {
                return QEmployee.employee.lastName
                        .concat(StringUtils.SPACE)
                        .concat(QEmployee.employee.firstName)
                        .concat(StringUtils.SPACE)
                        .concat(QEmployee.employee.patronymic.coalesce(StringUtils.EMPTY))
                        .containsIgnoreCase(queryStr);
            }
            case String s
                when PHONE_PATTERN.matcher(s).matches() -> {
                var normalizePhone = s
                        .replaceAll("\\s", "")
                        .replaceAll("[+\\-()]", "");
                return QEmployee.employee.phone.eq(normalizePhone);

            }
            case String s
                    when EMAIL_PATTERN.matcher(s).matches() -> {
                return QEmployee.employee.email.equalsIgnoreCase(s);

            }
            default -> throw new IllegalArgumentException("Wrong query: " + queryStr);
        }

    }

}
