package com.greenmark.utils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTResult</p>
 * <p>Description: This object is meant to serve as a "result" from any operation. That is, often
 * you want to provide or be able to provide more information about an operation
 * than just a boolean.  For example, if you want to perform a database
 * transaction you need simply a "true" on success, but on failure you'll want to
 * know more about the error. Using this object can provide you with that
 * flexibility. Towards that end there are four constructors:
 * <ol>
 * <li>Object returns only a boolean success flag.
 * <li>Object returns success flag and an object.
 * <li>Object returns success flag, error code and error description
 * <li>Object returns success flag, error code, error description and an object.
 * </ol>
 * The return data object was provided to allow the return of an object. It is
 * generic and the user should cast the object when using it.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTResult {
    public static final String CLASSNAME = "UTResult";

    protected boolean successCode = true;
    protected String errorCode = "";
    protected String errorDescription = "";
    protected Object returnObject;

    /////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////

    /**
     * Empty constructor
     */
    public UTResult() {
    }

    /**
     * UTResult object constructor: should only be used in the case of 'true' or successful operations.
     *
     * @param <code>successCode</code>Boolean flag signifying operations success or failure
     */
    public UTResult(boolean successCode) {
        // Set what was sent in.
        setSuccessCode(successCode);

        // Set the non-used items
        String StrEmpty = "";
        Object objNone = new Object();
        setErrorCode(StrEmpty);
        setErrorDescription(StrEmpty); // set to empty rather than null to avoid null pointer exception
        this.returnObject = null;
    }

    /**
     * UTResult object constructor: more detailed 'success' constructor, use this in situations where you dont want to set the description.
     *
     * @param <code>successCode</code>Boolean flag signifying operations success or failure
     * @param <code>errorCode</code>A         String error number.
     */
    public UTResult(boolean successCode, String errorCode) {
        // Set what was sent in.
        setSuccessCode(successCode);
        setErrorCode(errorCode);

        // Set the non-used items
        String StrEmpty = "";
        setErrorDescription(StrEmpty); // set to empty rather than null to avoid null pointer exception
        this.returnObject = null;
    }

    /**
     * UTResult object constructor: more detailed 'success' constructor, use this in situations where a object also needs to be returned.
     *
     * @param <code>successCode</code>Boolean flag signifying operations success or failure
     * @param <code>returnObject</code>An     object to be returned.
     */
    public UTResult(boolean successCode, Object rObject) {
        // Set what was sent in.
        setSuccessCode(successCode);
        setReturnObject(rObject);

        // Set the non-used items
        String StrEmpty = "";
        setErrorCode(StrEmpty);
        setErrorDescription(StrEmpty); // set to empty rather than null to avoid null pointer exception
    }

    /**
     * UTResult object constructor: use this in cases of errors.
     *
     * @param <code>successCode</code>Boolean    flag signifying operations success or failure
     * @param <code>errorCode</code>A            String error number.
     * @param <code>errorDescription</code>Human readable error message (not for end user)
     */
    public UTResult(boolean successCode, String errorCode, String errorDescription) {
        // Set what was sent in.
        setSuccessCode(successCode);
        setErrorCode(errorCode);
        setErrorDescription(errorDescription);

        // Set the non-used items
        this.returnObject = null;
        // Object objNone = new Object();
        // setReturnObject(objNone); //set to generic
    }

    /**
     * UTResult object constructor: use this in cases of errors.
     *
     * @param <code>successCode</code>Boolean    flag signifying operations success or failure
     * @param <code>errorCode</code>A            String error number.
     * @param <code>errorDescription</code>Human readable error message (not for end user)
     * @param <code>returnObject</code>An        object to be returned.
     */
    public UTResult(boolean successCode, String errorCode, String errorDescription, Object rObject) {
        // Set what was sent in.
        setSuccessCode(successCode);
        setErrorCode(errorCode);
        setErrorDescription(errorDescription);
        setReturnObject(rObject);
    }

    /////////////////////////////////////////////////
    // PUBLIC METHODS
    /////////////////////////////////////////////////

    /* #################### IS SUCCESSFUL #################### */

    /**
     * This method returns whether the Operation was successful or not. It makes the code more readable. e.g.
     * <p>
     * if (UTResult.isSuccessful()) { // do something }
     */
    public boolean isSuccessful() {
        return successCode;
    }

    /* #################### IS NOT SUCCESSFUL #################### */

    /**
     * This method returns whether the Operation was successful or not. It makes the code more readable. e.g.
     * <p>
     * if (UTResult.isNotSuccessful()) { // do something }
     */
    public boolean isNotSuccessful() {
        // do the reverse
        return !this.successCode;
    }

    /* #################### SUCCESS CODE #################### */

    /**
     * This method returns whether the Operation was successful or not. It is the same Method as isSuccessful() e.g.
     * <p>
     * if (UTResult.isSuccessful()) { // do something }
     */
    public boolean getSuccessCode() {
        return successCode;
    }

    /**
     * The method sets the Success Code.
     */
    public void setSuccessCode(boolean successCode) {
        this.successCode = successCode;
    }

    /* #################### ERROR CODE #################### */

    /**
     * The error code is a value that signifies the error. The convention is to use a code that is all capitalized letters, and underscore characters. (e.g. INVALID_DATA).
     * <p>
     * The value is not intended to be displayed to an end user. A programmer using the API would see the errorCode, and then do the appropriate thing in their own program.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * The method sets the Error Code.
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /* #################### ERROR DESCRIPTION #################### */

    /**
     * The errorDescription is an english language description of the cause of the error. This could be printed out to a log file, or printed in an HTML comment, to help debug errors.
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * The method sets the Error description.
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    /* #################### RETURN OBJECT #################### */

    /**
     * The return data is an object that returned by the operation. The user should cast this object to the appropriate type when using this function.
     */
    public Object getReturnObject() {
        return returnObject;
    }

    /**
     * The method sets the Return object.
     */
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    ///////////////////////////////////////////////////////////////////////////////

    /**
     * The method returns the UTResult as a String
     */
    public String toString() {
        String ls = System.getProperty("line.separator");
        return ls + "successCode:\t" + getSuccessCode() + ls + "errorCode:\t" + getErrorCode() + ls + "errorDescript:\t" + getErrorDescription() + ls;
    }

    /**
     * Returns the UTResult as well-formed XML
     */
    public String toXML() {
        String ls = System.getProperty("line.separator");

        return "<UTResult>" + ls + "<successCode>" + getSuccessCode() + "</successCode>" + ls + "<errorCode>" + getErrorCode() + "</errorCode>" + ls + "<errorDescription>" + getErrorDescription()
                + "</errorDescription>" + ls + "</UTResult>";
    }

}
