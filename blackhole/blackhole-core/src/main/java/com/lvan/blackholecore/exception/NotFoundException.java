package com.lvan.blackholecore.exception;

/**
 * @author Lvan
 * @since 2021/8/1
 */
public class NotFoundException extends DataBaseException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String template, Object... params) {
        super(template, params);
    }
}
