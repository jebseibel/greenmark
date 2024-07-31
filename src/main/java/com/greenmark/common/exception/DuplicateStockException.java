package com.greenmark.common.exception;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DuplicateStockException</p>
 * <p>Description: This exception is thrown when the user tries to add the same security to a timeframe bucket that already contains that security.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DuplicateStockException extends Exception {
    public static final String CLASSNAME = "DuplicateStockException";

    private static final long serialVersionUID = 1L;

    /**
     * The message that will be included in this exception for display to the user.
     */
    protected String message;

    public DuplicateStockException() {
    }

    /**
     * This constructor creates an DuplicateStockException object and initializes its message attribute with the input messageString parameter.
     *
     * @param messageString Any message that will be included in this exception for display to the user.
     * @return This Object
     */
    public DuplicateStockException(String messageString) {
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
