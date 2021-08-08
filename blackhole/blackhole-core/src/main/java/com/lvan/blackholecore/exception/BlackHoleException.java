package com.lvan.blackholecore.exception;

/**
 *
 * Runtime exception that is the superclass of all BlackHole exceptions.
 *
 * @author Lvan
 * @since 2021/7/31
 */
public class BlackHoleException extends RuntimeException {

    private static final long serialVersionUID = 5644041446200336816L;

    public BlackHoleException() {
    }

    public BlackHoleException(String message) {
        super(message);
    }
}
