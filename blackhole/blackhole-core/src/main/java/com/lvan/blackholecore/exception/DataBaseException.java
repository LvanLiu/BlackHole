package com.lvan.blackholecore.exception;

/**
 * 是所有与数据库相关异常的基类。
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
