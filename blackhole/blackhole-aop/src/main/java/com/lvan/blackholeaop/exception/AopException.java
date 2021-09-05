package com.lvan.blackholeaop.exception;

import com.lvan.blackholecore.exception.BlackHoleException;

/**
 * @author Lvan
 * @since 2021/9/5
 */
public class AopException extends BlackHoleException {

    public AopException() {
        super();
    }

    public AopException(String message) {
        super(message);
    }

    public AopException(String template, Object... params) {
        super(template, params);
    }
}
