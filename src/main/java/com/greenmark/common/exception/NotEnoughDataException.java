package com.greenmark.common.exception;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: NotEnoughDataException</p>
 * <p>Description: This exception is used by the Prices DB Updater application if it queries the external data feed and the number of data points for a day, for that timeframe, are not returned.
 *    For example, we would expect 24 hourly data points in a day and if the datafeed does not return 24 data points, we use this exception.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class NotEnoughDataException extends Exception {
    public static final String CLASSNAME = "NotEnoughDataException";

    private static final long serialVersionUID = 1L;

    /**
     * The message that will be included in this exception for display to the user.
     */
    protected String message;

    public NotEnoughDataException() {
    }

    /**
     * This constructor creates an NotEnoughDataException object and initializes its message attribute with the input messageString parameter.
     *
     * @param messageString Any message that will be included in this exception for display to the user.
     * @return This Object
     */
    public NotEnoughDataException(String messageString) {
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
