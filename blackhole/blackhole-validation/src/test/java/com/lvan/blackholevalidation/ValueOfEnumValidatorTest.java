package com.lvan.blackholevalidation;

import com.lvan.blackholevalidation.api.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lvan
 * @since 2021/8/15
 */
class ValueOfEnumValidatorTest {
    private static final Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void whenAllAcceptable_thenShouldNotGiveConstraintViolations() {
        Customer customer = new Customer();
        customer.setType(CustomerType.VIP.code);
        Set<ConstraintViolation<Customer>> customerValidates = validator.validate(customer);

        Person person = new Person();
        person.setSex(Sex.MALE.name());
        Set<ConstraintViolation<Person>> personValidates = validator.validate(person);

        assertThat(customerValidates).isEmpty();
        assertThat(personValidates).isEmpty();
    }

    @Test
    void whenAllNULL_thenOnlyNotNullShouldGiveConstraintViolations() {
        Customer customer = new Customer();
        Set<ConstraintViolation<Customer>> validate = validator.validate(customer);
        assertThat(validate).isNotEmpty();
    }

    @Test
    void whenAllInvalid_thenShouldGiveConstraintViolations() {
        Customer customer = new Customer();
        customer.setType(0);
        Set<ConstraintViolation<Customer>> validate = validator.validate(customer);

        Person person = new Person();
        person.setSex(Sex.MALE.name().toLowerCase());
        Set<ConstraintViolation<Person>> personValidates = validator.validate(person);

        assertThat(validate).isNotEmpty();
        assertThat(personValidates).isNotEmpty();
    }

    @Test
    void whenEnumMethodIsInvalid_thenShouldGiveException() {

        SuperVipCustomer superVipCustomer = new SuperVipCustomer();
        superVipCustomer.setType(0);

        Assertions.assertThrows(ValidationException.class, () -> {
            validator.validate(superVipCustomer);
        });
    }

    @Data
    static class Customer {
        @NotNull
        @ValueOfEnum(enumClass = CustomerType.class, method = ValueOfEnum.EnumMethodConstants.METHOD_GET_CODE)
        private Integer type;
    }

    @Data
    static class SuperVipCustomer {
        @ValueOfEnum(enumClass = CustomerType.class, method = "getType")
        private Integer type;
    }

    @Data
    static class Person {
        @ValueOfEnum(enumClass = Sex.class)
        private String sex;
    }

    @Getter
    @AllArgsConstructor
    enum CustomerType {
        NORMAL(1, "normal"),
        VIP(2, "vip"),
        ;
        private final int code;
        private final String desc;
    }

    @Getter
    enum Sex {
        MALE, FEMALE;
    }
}
