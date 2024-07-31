package com.greenmark.common.exception;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: InvalidSymbolException</p>
 * <p>Description: Currently not used.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

@Deprecated
public class InvalidSymbolException extends Exception {
    public static final String CLASSNAME = "InvalidSymbolException";

    private static final long serialVersionUID = 1L;
    /**
     * The message that will be included in this exception for display to the user.
     */
    protected String message;

    public InvalidSymbolException() {
    }

    /**
     * This constructor creates an InvalidSymbolException object and initializes its message attribute with the input messageString parameter.
     *
     * @param messageString Any message that will be included in this exception for display to the user.
     * @return This Object
     */
    public InvalidSymbolException(String messageString) {
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
