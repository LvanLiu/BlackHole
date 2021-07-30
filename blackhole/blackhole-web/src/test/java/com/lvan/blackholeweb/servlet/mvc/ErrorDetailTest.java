package com.lvan.blackholeweb.servlet.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Lvan
 * @since 2021/7/30
 */
class ErrorDetailTest {

    @Test
    void error_whenCodeIsNull_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () ->
                ErrorDetail.error("type", null, "message"));
    }

    @Test
    void error_whenMessageIsNull_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () ->
                ErrorDetail.error("type", 0, null));
    }

    @Test
    void error_whenCodeAndMessageIsNonNull_errorDetailIsNonNull() {

        ErrorDetail errorDetail = ErrorDetail.error("type", 0, "message");
        assertNotNull(errorDetail);
    }
}