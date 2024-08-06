package com.greenmark.common.utils;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTValidator</p>
 * <p>Description: A convenience class to validate primitives.  Currently not used.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTValidator {
    private static final String CLASSNAME = "UTValidator";

    /////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////

    /**
     * No constructor
     */

    /////////////////////////////////////////////////
    // PUBLIC METHODS
    /////////////////////////////////////////////////

    /**
     * Validates that the sent in value is a valid string.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     */
    public static UTResult validateString(String value) {
        return UTValidator.validateString(value, "");
    }

    /**
     * Validates that the sent in value is a valid string.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     * @param <code>error</code> The error value returned on error.
     */
    public static UTResult validateString(String value, String error) {
        try {
            // check for null or zero length
            if (UTUtils.isNorE(value)) {
                return new UTResult(false, error, "The value is empty or null.");
            }
            return new UTResult(true);
        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ". validateString, message: [" + e.getMessage() + "]");
        }
        return new UTResult(false, "exception error", "Exception caught in routine: validateString");
    }

    ///////////////////////////////////////////////////////////////////////

    /**
     * Validates that the sent in value is a valid number.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     */
    public static UTResult validateNumber(String value) {
        return UTValidator.validateNumber(value, "");
    }

    /**
     * Validates that the sent in value is a valid number.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     * @param <code>error</code> The error value returned on error.
     */
    public static UTResult validateNumber(String value, String error) {
        try {
            // check for null or zero length
            if (UTUtils.isNorE(value)) {
                return new UTResult(false, error, "The value is empty or null.");
            }

            // OTHER CHECKS
            char[] characters = value.toCharArray();

            // Start by checking for an empty string
            if (value.length() == 0) {
                return new UTResult(false, error, "The value is empty or null.");
            }

            // Cycle through the array of characters.
            // Bail at the first sight of a non-digit
            for (int i = 0; i < characters.length; i++) {
                if (!Character.isDigit(characters[i])) {
                    return new UTResult(false, error, "The number type contained a non-digit character.");
                }

            }
            // if we get this far, we are okay
            return new UTResult(true);
        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ". validateString, message: [" + e.getMessage() + "]");
        }
        return new UTResult(false, "exception error", "Exception caught in routine: validateNumber.");
    }

    ///////////////////////////////////////////////////////////////////////

    /**
     * Validates that the sent in value is a valid currency.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     */
    public static UTResult validateCurrency(String value) {
        return UTValidator.validateCurrency(value, "");
    }

    /**
     * Validates that the sent in value is a valid currency.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     * @param <code>error</code> The error value returned on error.
     */
    public static UTResult validateCurrency(String value, String error) {
        try {
            // check for null or zero length
            if (UTUtils.isNorE(value)) {
                return new UTResult(false, error, "The value is empty or null.");
            }

            // OTHER CHECKS
            char[] characters = value.toCharArray();

            // Start by checking for an empty string
            if (value.length() == 0) {
                return new UTResult(false, error, "The value is empty or null.");
            }

            // they are only allowed one decimal
            boolean decimal_notfound = true;

            // Cycle through the array of characters.
            // Bail at the first sight of a non-digit
            for (int i = 0; i < characters.length; i++) {
                if (!Character.isDigit(characters[i])) {
                    if (characters[i] == '.' && decimal_notfound) {
                        decimal_notfound = false;
                    } else {
                        return new UTResult(false, error, "The number type contained a non-digit character.");
                    }
                }

            }
            // if we get this far, we are okay
            return new UTResult(true);
        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ". validateCurrency, message: [" + e.getMessage() + "]");
        }
        return new UTResult(false, "exception error", "Exception caught in routine: validateNumber.");
    }

    ///////////////////////////////////////////////////////////////////////

    /**
     * Validates that the sent in value is a valid integer.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     */
    public static UTResult validateInt(String value) {
        return UTValidator.validateInt(value, "");
    }

    /**
     * Validates that the sent in value is a valid integer.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     * @param <code>error</code> The error value returned on error.
     */
    public static UTResult validateInt(String value, String error) {
        try {
            // check for null or zero length
            if (UTUtils.isNorE(value)) {
                return new UTResult(false, error, "The value is empty or null.");
            }

            // OTHER CHECKS
            char[] characters = value.toCharArray();

            // Start by checking for an empty string
            if (value.length() == 0) {
                return new UTResult(false, error, "The value is empty or null.");
            }

            // Cycle through the array of characters.
            // Bail at the first sight of a non-digit
            for (int i = 0; i < characters.length; i++) {
                if (!Character.isDigit(characters[i])) {
                    return new UTResult(false, error, "The string contained a non-digit character.");
                }
            }
            // if we get this far, we are okay
            return new UTResult(true);
        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ". validateInt, message: [" + e.getMessage() + "]");

        }
        return new UTResult(false, "exception error", "Exception caught in routine: validateInt");
    }

    ///////////////////////////////////////////////////////////////////////

    /**
     * Validates that the sent in value is a valid yes/no string.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     */
    public static UTResult validateYesNo(String value) {
        return UTValidator.validateYesNo(value, "");
    }

    /**
     * Validates that the sent in value is a valid yes/no string.
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     * @param <code>error</code> The error value returned on error.
     */
    public static UTResult validateYesNo(String value, String error) {
        try {
            // check for null or zero length
            if (UTUtils.isNorE(value)) {
                return new UTResult(false, error, "The value is empty or null.");
            }

            // OTHER CHECKS
            String lc = value.toLowerCase();

            if (lc.equals("y") || lc.equals("n")) {
                return new UTResult(true, "VALID_YES_OR_NO", "The value passed was Y or N");
            } else {
                return new UTResult(false, error, "The value passed was not Y or N");
            }
        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ". validateYesNo, message: [" + e.getMessage() + "]");

        }
        return new UTResult(false, "exception error", "Exception caught in routine: validateYesNo");
    }

    ///////////////////////////////////////////////////////////////////////

    /**
     * Validates that the sent in value is a valid datetime. The valid format is: <blockquote><code>
     * MM/DD/YYYY HH/MM/SS or <br>
     * MM/DD/YYYY HH/MM/SS
     * </blockquote></code> There are further restrictions on both the date and the time.
     * <p>
     * A valid date has the following format. <blockquote><code>
     * MM/DD/YYYY
     * </blockquote></code> With the following restrictions:
     * <ul>
     * <li>Year must contain four digits
     * <li>Month must contain one or two digits
     * <li>Day must contain one or two digits
     * <li>Month is between 1 and 12
     * <li>Day must be less than 32 and appropriate for the month
     * <li>Leap year is also considered.
     * </ul>
     * <p>
     * A valid time has the following format. <blockquote><code>
     * HH:MM:SS
     * </blockquote></code> With the following restrictions:
     * <ul>
     * <li>Hours (HH) must contain one or two digits, and be between 0-23 (inclusive)
     * <li>Minutes (MM) must contain one or two digits, and be between 0-59 (inclusive)
     * <li>Seconds (SS) must contain one or two digits, and be between 0-59 (inclusive)
     * </ul>
     * <p>
     *
     * @param <code>value</code> The value to be tested.
     */
    public static UTResult validateDatetime(String value) {
        return UTValidator.validateDatetime(value, "");
    }

    /**
     * Validates that the sent in value is a valid datetime. The valid format is: <blockquote><code>
     * MM/DD/YYYY HH/MM/SS or <br>
     * MM/DD/YYYY HH/MM/SS
     * </blockquote></code> There are further restrictions on both the date and the time.
     * <p>
     * A valid date has the following format. <blockquote><code>
     * MM/DD/YYYY
     * </blockquote></code> With the following restrictions:
     * <ul>
     * <li>Year must contain four digits
     * <li>Month must contain one or two digits
     * <li>Day must contain one or two digits
     * <li>Month is between 1 and 12
     * <li>Day must be less than 32 and appropriate for the month
     * <li>Leap year is also considered.
     * </ul>
     * <p>
     * A valid time has the following format. <blockquote><code>
     * HH:MM:SS
     * </blockquote></code> With the following restrictions:
     * <ul>
     * <li>Hours (HH) must contain one or two digits, and be between 0-23 (inclusive)
     * <li>Minutes (MM) must contain one or two digits, and be between 0-59 (inclusive)
     * <li>Seconds (SS) must contain one or two digits, and be between 0-59 (inclusive)
     * </ul>
     * <p>
     *
     * @param <code>value</code> Datetime to be tested.
     * @param <code>error</code> The error to be returned on failure.
     */
    public static UTResult validateDatetime(String value, String error) {
        boolean debug = false;
        try {
            // check for null or zero length
            if (UTUtils.isNorE(value)) {
                return new UTResult(false, error, "The value is empty or null.");
            }

            // OTHER CHECKS

            // Look for a space between the date and time sections
            int ind1 = value.indexOf(' ');
            boolean hasTime = ind1 != -1;
            //////////////////////////////////////
            // Split the date and time
            String StrDate = value;
            String StrTime = "";
            if (hasTime) {
                StrDate = value.substring(0, ind1);
                StrTime = value.substring(ind1 + 1);
            }

            // Verify the input
            UTResult dResult = UTValidator.validateDate(StrDate, error);
            if (!dResult.isSuccessful()) {
                return dResult;
            }

            // Verify the input
            if (hasTime) {
                UTResult tResult = UTValidator.validateLongTime(StrTime, "");
                if (!tResult.isSuccessful()) {
                    return tResult;
                }
            }

            // If we did't bail by now, we're OK
            return new UTResult(true);
        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ". validateDatetime, message: [" + e.getMessage() + "]");

        }
        return new UTResult(false, "exception error", "Exception caught in routine: validateDatetime");
    }

    /**
     * Checks for a valid date: format and valid date(including leapyears).<br>
     * A valid date has the following format. <blockquote><code>
     * MM/DD/YYYY
     * </blockquote></code> With the following restrictions:
     * <ul>
     * <li>Year must contain four digits or two digits
     * <li>Month must contain one or two digits
     * <li>Day must contain one or two digits
     * <li>Month is between 1 and 12
     * <li>Day must be less than 32 and appropriate for the month
     * <li>Leap year is also considered.
     * </ul>
     * <p>
     *
     * @param <code>date</code> The date to be checked.
     */
    public static UTResult validateDate(String value) {
        return UTValidator.validateDate(value, "");
    }

    /**
     * Checks for a valid date: format and valid date(including leapyears).<br>
     * A valid date has the following format. <blockquote><code>
     * YYYY-MM-DD
     * </blockquote></code> With the following restrictions:
     * <ul>
     * <li>Year must contain four digits or two digits
     * <li>Month must contain one or two digits
     * <li>Day must contain one or two digits
     * <li>Month is between 1 and 12
     * <li>Day must be less than 32 and appropriate for the month
     * <li>Leap year is also considered.
     * </ul>
     * <p>
     *
     * @param <code>date</code> The date to be checked.
     */
    public static UTResult validateMSDate(String value) {
        return UTValidator.validateMSDate(value, "");
    }

    public static UTResult validateMSDate(String value, String error) {
        StringBuffer stb = new StringBuffer();
        try {
            // build new date
            int pc1 = value.indexOf("-");
            int pc2 = value.lastIndexOf("-");
            String year = value.substring(0, pc1);
            String mon = value.substring(pc1 + 1, pc2);
            String day = value.substring(pc2 + 1);

            System.out.println(" . . . mon [" + mon + "]  day [" + day + "]  year [" + year + "]");
            stb.append(mon + "-" + day + "-" + year);

            return UTValidator.validateDate(stb.toString(), "");
        } catch (Exception e) {
            return new UTResult(false, error, "The MSdate submitted is in error.");
        }
    }

    /**
     * Checks for a valid date: format and valid date(including leapyears).<br>
     * A valid date has the following format. <blockquote><code>
     * MM-DD-YYYY
     * </blockquote></code> With the following restrictions:
     * <ul>
     * <li>Year must contain four digits or two digits
     * <li>Month must contain one or two digits
     * <li>Day must contain one or two digits
     * <li>Month is between 1 and 12
     * <li>Day must be less than 32 and appropriate for the month
     * <li>Leap year is also considered.
     * </ul>
     * <p>
     *
     * @param <code>date</code>     The date to be checked.
     * @param <code>error</code>The error value returned on error.
     */
    public static UTResult validateDate(String value, String error) {
        boolean debug = true;

        // Initialize information
        Hashtable MonthDays = new Hashtable();

        MonthDays.put("1", "31");
        MonthDays.put("2", "28");
        MonthDays.put("3", "31");
        MonthDays.put("4", "30");
        MonthDays.put("5", "31");
        MonthDays.put("6", "30");
        MonthDays.put("7", "31");
        MonthDays.put("8", "31");
        MonthDays.put("9", "30");
        MonthDays.put("10", "31");
        MonthDays.put("11", "30");
        MonthDays.put("12", "31");

        // check for null or zero length
        if (UTUtils.isNorE(value)) {
            return new UTResult(false, error, "The date submitted is empty or null.");
        }

        // Now, check the number areas, to see if they are valid numbers
        int ind1 = value.indexOf('-');
        int ind2 = value.lastIndexOf('-');

        if (ind1 == -1) {
            ind1 = value.indexOf('-');
            ind2 = value.lastIndexOf('-');
        }

        if ((ind1 == ind2) || (ind1 == -1)) {
            return new UTResult(false, error, "The date submitted was of invalid format. Please use: MM-DD-YYYY");
        }

        String day = "";
        String month = "";
        String year = "";

        month = value.substring(0, ind1);
        day = value.substring(ind1 + 1, ind2);
        year = value.substring(ind2 + 1);

        // Try converting each to an Integer
        Integer iday = Integer.valueOf(0);
        Integer imonth = Integer.valueOf(0);
        Integer iyear = Integer.valueOf(0);

        try {
            iday = Integer.valueOf(day);
            imonth = Integer.valueOf(month);
            iyear = Integer.valueOf(year);

            // Now do specific checks

            // Verify the month
            if ((imonth.intValue() < 1) || (imonth.intValue() > 12)) {
                return new UTResult(false, error, "The month submitted is not valid");
            }

            // Handle leap-years
            int leap = iyear.intValue() % 4;
            if (leap == 0) {
                // change month/day hashtable to appropriate days
                MonthDays.put("2", "29");
            }

            // Verify the day
            // A month never has more than 31 days
            if ((iday.intValue() < 1) || (iday.intValue() > 31)) {
                return new UTResult(false, error, "The day submitted is not valid.");
            } else {
                String thisMon = "";
                String thisDay = "";
                // Step through the hashtable one at a time, retrieving the Value and Key.
                // We're going to retrieve the number of days for the input month
                for (Enumeration e = MonthDays.keys(); e.hasMoreElements(); ) {

                    // Grab the current Key and Value.
                    thisMon = (String) e.nextElement();
                    thisDay = (String) MonthDays.get(thisMon);
                    Integer intMon = Integer.valueOf(thisMon);
                    Integer intDay = Integer.valueOf(thisDay);

                    // looking for this month.
                    if (imonth.intValue() == intMon.intValue()) {
                        // see if the day is valid
                        if (!(iday.intValue() <= intDay.intValue())) {
                            return new UTResult(false, error, "The day you supplied is not valid for the month");
                        }
                    } // End if
                } // End For
            }

        } catch (NumberFormatException nfe) {
            return new UTResult(false, error, "The day, month and year must be numbers.");
        }

        // If we did't bail by now, we're OK
        return new UTResult(true, "", "The date you supplied is valid.");
    }

    /**
     * This is a validation method that checks time element.
     * <p>
     * A string containing the time consists of a 2 digit hour, minute, and seconds.<br>
     * It must be of the following formats:
     * <ul>
     * <li>HH:MM:SS AM
     * <li>HH:MM:SS PM
     * </ul>
     * Note: AM/PM can be of any type case (upper, lower, or mixed).
     * <p>
     *
     * @param <code>value</code> The value to be checked in.
     */
    public static UTResult validateLongTime(String value) {
        return UTValidator.validateLongTime(value, "");
    }

    /**
     * This is a validation method that checks time element.
     * <p>
     * A string containing the time consists of a 2 digit hour, minute, and seconds.<br>
     * It must be of the following formats:
     * <ul>
     * <li>HH:MM:SS AM
     * <li>HH:MM:SS PM
     * </ul>
     * Note: AM/PM can be of any type case (upper, lower, or mixed).
     * <p>
     *
     * @param <code>value</code> The value to be checked in.
     * @param <code>error</code> The error value returned on error.
     */
    public static UTResult validateLongTime(String value, String error) {
        String StrHrs;
        String StrMin;
        String StrSec;

        int inthrsIndex;
        int intminIndex;
        int intsecIndex;

        // check for null or zero length
        if (UTUtils.isNorE(value)) {
            return new UTResult(false, error, "The value is empty or null.");
        }

        // Check to see if value is proper length
        if ((value.length() > 11) || (value.length() < 0)) {
            return new UTResult(false, error, "The Time must be either 8 characters (HH:MM:SS) or 11 characters (HH:MM:SS PM/AM).");
        }

        // Gets value at specific indices within the array.
        // First check to see if the time is in military or standard, if it is standard check
        // if it is am or pm.

        value = value.toLowerCase();
        int intAmIndex = value.indexOf(" am");
        int intPmIndex = value.indexOf(" pm");

        if (value.length() != 8) {
            // if loop is true then time is standard AM. The string is trimmed to remove AM.
            /////////////// AM
            if (intAmIndex != -1) {
                value = value.substring(0, intAmIndex);
                value = value.trim();
            } else if (intPmIndex != -1) {
                // if loop is true then time is standard PM. The string is trimmed to remove PM.
                value = value.substring(0, intPmIndex);
                value = value.trim();
            } else {
                return new UTResult(false, error, "Malformed time sent in (am/pm).");
            }
        } else {
            return new UTResult(false, error, "Malformed time sent in (am/pm).");
        }

        // Series of Statements to retrieve Hrs, min, sec, and mls. These statements all have three parts:
        // an index to search for the location of a colon or period, a boolcheck to determine if a part of the
        // time has not been entered. For example 12:00:00.000 -> 12:00.000, hrs has not been entered. Finally,
        // the variable to store the value of time.
        try {

            // HOURS
            // if statement is true then an hrs field has been found
            inthrsIndex = value.indexOf(":");
            StrHrs = value.substring(0, inthrsIndex);

            // MINUTES
            // if statement is true then a second field has been found
            intsecIndex = value.indexOf(":", inthrsIndex + 1);
            StrMin = value.substring(inthrsIndex + 1, intsecIndex);

            // SECONDS
            // if statement is true then a min field has been found
            StrSec = value.substring(intsecIndex + 1);
        } catch (Exception eMal) {
            return new UTResult(false, error, "Malformed time sent in: 'HH:MM:SS') ");
        }

        // Perform checks to see if value is within range of Time parameters
        if (!UTValidator.validateNumberSize(StrHrs, 1, 12, error).isSuccessful()) {

            return new UTResult(false, error, "The hour value is out of range.");
        }

        if (!UTValidator.validateNumberSize(StrMin, 0, 59, error).isSuccessful()) {

            return new UTResult(false, error, "The minute value is out of range.");
        }

        if (!UTValidator.validateNumberSize(StrSec, 0, 59, error).isSuccessful()) {

            return new UTResult(false, error, "The second value is out of range.");
        }

        return new UTResult(true, "The time is valid.");
    }

    /**
     * This is a validation method that checks time element.
     * <p>
     * A string containing the time consists of a 2 digit hour and minutes.<br>
     * It must be of the following formats:
     * <ul>
     * <li>HH:MM AM
     * <li>HH:MM PM
     * </ul>
     * Note: AM/PM can be of any type case (upper, lower, or mixed).
     * <p>
     *
     * @param <code>value</code> The value to be checked in.
     */
    public static UTResult validateShortTime(String value) {
        return UTValidator.validateShortTime(value, "");
    }

    /**
     * This is a validation method that checks time element.
     * <p>
     * A string containing the time consists of a 2 digit hour and minutes.<br>
     * It must be of the following formats:
     * <ul>
     * <li>HH:MM AM
     * <li>HH:MM PM
     * </ul>
     * Note: AM/PM can be of any type case (upper, lower, or mixed).
     * <p>
     *
     * @param <code>value</code> The value to be checked in.
     * @param <code>error</code> The error value returned on error.
     */
    public static UTResult validateShortTime(String value, String error) {
        String StrHrs;
        String StrMin;
        String StrSec;

        int inthrsIndex;
        int intminIndex;
        int intsecIndex;

        // check for null or zero length
        if (UTUtils.isNorE(value)) {
            return new UTResult(false, error, "The value is empty or null.");
        }

        // Check to see if value is proper length
        if (value.length() > 8) {
            return new UTResult(false, error, "The time must be 8 characters (HH:MM)");
        }

        // Check to see if it has an AM or PM.

        value = value.toLowerCase();
        int intAmIndex = value.indexOf(" am");
        int intPmIndex = value.indexOf(" pm");

        // if loop is true then remove the AM.
        if (intAmIndex != -1) {
            value = value.substring(0, intAmIndex);
            value = value.trim();
        } else if (intPmIndex != -1) {
            // if loop is true then remove the PM.
            value = value.substring(0, intPmIndex);
            value = value.trim();
        } else {
            return new UTResult(false, error, "Malformed time sent in (am/pm).");
        }

        // Series of Statements to retrieve hours and minutes. These statements all have three parts:
        // an index to search for the location of a colon or period, a boolean check to determine if a part of the
        // time has not been entered. Finally, the variable to store the value of time.
        try {
            // HOURS
            // if statement is true then an hrs field has been found
            inthrsIndex = value.indexOf(":");
            StrHrs = value.substring(0, inthrsIndex);
            StrMin = value.substring(inthrsIndex + 1);

            if (StrHrs.length() != 2) {
                return new UTResult(false, error, "Malformed time sent in: hours must be 2 digits long.) ");
            }

            if (StrMin.length() != 2) {
                return new UTResult(false, error, "Malformed time sent in: minutes must be 2 digits long.) ");
            }
        } catch (Exception eMal) {
            return new UTResult(false, error, "Malformed time sent in: 'HH:MM') ");
        }

        // Perform checks to see if value is within range of Time parameters
        if (!UTValidator.validateNumberSize(StrHrs, 0, 23, error).isSuccessful()) {
            return new UTResult(false, error, "The hour value is out of range.");
        }

        if (!UTValidator.validateNumberSize(StrMin, 0, 59, error).isSuccessful()) {
            return new UTResult(false, error, "The minute value is out of range.");
        }

        return new UTResult(true, "The time is valid.");
    }

    /**
     * This is a validation method that check a number size.
     * <p>
     * The method accepts a minimum and a maximum value. The value passed in is then compared against these two values. If the value is equal to or greater than the minimum value and less than or
     * equal the maximum value a success=true is returned.
     * <p>
     * For clarity, here is a mathematical equivalent:<br>
     * <code>minimum <= value <= maximum</code>
     * <p>
     * Note: all values are translated to type <code>long</code> for comparision.
     * <p>
     *
     * @param <code>value</code> The value to be checked.
     * @param <code>min</code>   The minimum value as a long value.
     * @param <code>max</code>   The maximum value as a long value.
     */
    public static UTResult validateNumberSize(String value, String min, String max) {
        return UTValidator.validateNumberSize(value, min, max, "");
    }

    /**
     * This is a validation method that check a number size.
     * <p>
     * The method accepts a minimum and a maximum value. The value passed in is then compared against these two values. If the value is equal to or greater than the minimum value and less than or
     * equal the maximum value a success=true is returned.
     * <p>
     * For clarity, here is a mathematical equivalent:<br>
     * <code>minimum <= value <= maximum</code>
     * <p>
     * Note: all values are translated to type <code>long</code> for comparision.
     * <p>
     *
     * @param <code>value</code> The value to be checked.
     * @param <code>min</code>   The minimum value as a long value.
     * @param <code>max</code>   The maximum value as a long value.
     * @param <code>error</code> The error value returned on error.
     */
    public static UTResult validateNumberSize(String value, String min, String max, String error) {
        try {
            if (UTUtils.isNorE(min)) {
                return new UTResult(false, error, "The minimum value in UTValidator.validateNumberSize() is empty or null.");
            }
            if (UTUtils.isNorE(max)) {
                return new UTResult(false, error, "The maximum value in UTValidator.validateNumberSize() is empty or null.");
            }

            long lmin = Long.parseLong(min);
            long lmax = Long.parseLong(max);
            return UTValidator.validateNumberSize(value, lmin, lmax, error);
        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ". validateNumberSize, message: [" + e.getMessage() + "]");

        }
        return new UTResult(false, error, "The minimum or maximum value in UTValidator.validateNumberSize() is empty or null or not a long value");
    }

    /**
     * This is a validation method that check a number size.
     * <p>
     * The method accepts a minimum and a maximum value. The value passed in is then compared against these two values. If the value is equal to or greater than the minimum value and less than or
     * equal the maximum value a success=true is returned.
     * <p>
     * For clarity, here is a mathematical equivalent:<br>
     * <code>minimum <= value <= maximum</code>
     * <p>
     * Note: all values are translated to type <code>long</code> for comparision.
     * <p>
     *
     * @param <code>value</code> The value to be checked.
     * @param <code>min</code>   The minimum value as a string.
     * @param <code>max</code>   The maximum value as a string.
     */
    public static UTResult validateNumberSize(String value, long min, long max) {
        return UTValidator.validateNumberSize(value, min, max, "");
    }

    /**
     * This is a validation method that check a number size.
     * <p>
     * The method accepts a minimum and a maximum value. The value passed in is then compared against these two values. If the value is equal to or greater than the minimum value and less than or
     * equal the maximum value a success=true is returned.
     * <p>
     * For clarity, here is a mathematical equivalent:<br>
     * <code>minimum <= value <= maximum</code>
     * <p>
     * Note: all values are translated to type <code>long</code> for comparision.
     * <p>
     *
     * @param <code>value</code> The value to be checked.
     * @param <code>min</code>   The minimum value as a string.
     * @param <code>max</code>   The maximum value as a string.
     * @param <code>error</code> The error value returned on error.
     */

    public static UTResult validateNumberSize(String value, long min, long max, String error) {
        try {
            // check for null or zero length
            if (UTUtils.isNorE(value)) {
                return new UTResult(false, error, "The value in UTValidator.validateNumberSize() is empty or null.");
            }

            long lvalue = Long.parseLong(value);

            if (min > lvalue) {
                return new UTResult(false, error, "The number does not meet the minimum value allowed.");
            }

            if (max < lvalue) {
                return new UTResult(false, error, "The number exceeds the maximum value allowed.");
            }

            // If they're here, it's okay
            return new UTResult(true);
        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ". validateNumberSize2, message: [" + e.getMessage() + "]");
        }
        return new UTResult(false, error, "The value sent in is not a number.");

    }
}
