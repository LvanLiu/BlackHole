package com.lvan.blackholecore.exception;

/**
 * the base exception about database
 *
 * @author Lvan
 * @since 2021/8/1
 */
public class DataBaseException extends BlackHoleException {

    private static final long serialVersionUID = 1623286962808809807L;

    public DataBaseException() {
    }

    public DataBaseException(String message) {
        super(message);
    }
}
