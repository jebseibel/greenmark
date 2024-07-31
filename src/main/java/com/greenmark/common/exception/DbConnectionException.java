package com.greenmark.common.exception;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DbConnectionException</p>
 * <p>Description: This exception is thrown when we cannot obtain a database connection to either database.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DbConnectionException extends Exception {
    public static final String CLASSNAME = "DbConnectionException";

    private static final long serialVersionUID = 1L;

    /**
     * The message that will be included in this exception for display to the user.
     */
    protected String message;

    public DbConnectionException() {
    }

    public DbConnectionException(String messageString) {
        message = messageString;
    }

    /**
     * Public accessor to set the exception's message string.
     */
    public final String getMessage() {
        return message;
    }

    /**
     * Public accessor to return the exception's message string.
     */
    public final void setMessage(String newMessageString) {
        message = newMessageString;
    }
}
