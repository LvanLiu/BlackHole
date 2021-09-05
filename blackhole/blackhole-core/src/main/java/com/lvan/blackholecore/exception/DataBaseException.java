package com.lvan.blackholecore.exception;

/**
 * the base exception about database
 *
 * @author Lvan
 * @since 2021/8/1
 */
public class DataBaseException extends BlackHoleException {

    public DataBaseException() {
        super();
    }

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String template, Object... params) {
        super(template, params);
    }
}
