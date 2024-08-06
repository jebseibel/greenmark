package com.greenmark.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTUtils</p>
 * <p>Description: This class is a utility class that contains convenience methods for Strings and objects.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTUtils {
    public static final String CLASSNAME = "UTUtils";

    /////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////
    private UTUtils() {
        // can't call
    }

    /////////////////////////////////////////////////
    // STATIC METHODS
    /////////////////////////////////////////////////
    public static float findMinValue(float[] dataValues) {
        if (dataValues == null || dataValues.length == 0)
            return 0F;

        float returnValue = Integer.MAX_VALUE;

        for (float thisValue : dataValues) {
            if (thisValue < returnValue)
                returnValue = thisValue;
        }

        return returnValue;
    }

    public static float findMaxValue(float[] dataValues) {
        if (dataValues == null || dataValues.length == 0)
            return 0F;

        float returnValue = Integer.MAX_VALUE * -1;

        for (float thisValue : dataValues) {
            if (thisValue > returnValue)
                returnValue = thisValue;
        }

        return returnValue;
    }

    public static boolean isZero(double value) {
        return isThreshold(value, 0F);
    }

    public static boolean isZero(float value) {
        return isThreshold(value, 0F);
    }

    public static boolean isThreshold(double value, double threshold) {
        return value >= -threshold && value <= threshold;
    }

    public static boolean isThreshold(float value, float threshold) {
        return value >= -threshold && value <= threshold;
    }

    /* *********************************************************************** */
    /* ********************** VERIFY NULL FOR OBJECTS *********************** */

    /**
     * This is the static verification method. It checks to see if the object is null.
     *
     * @param <code>data</code>The object to be checked.
     * @@return - TRUE if null, FALSE if not null
     */
    public static boolean isNull(Object data) {
        return data == null;
    }

    /**
     * This is the static verification method. It checks to see if the object is not null.
     *
     * @param <code>data</code>The object to be checked.
     * @@return - TRUE if null, FALSE if not null
     */
    public static boolean isNotNull(Object data) {
        return data != null;
    }

    /* *********************************************************************** */
    /* ************** VERIFY NULL OR EMPUT FOR STRINGS ********************** */

    /**
     * This is the static verification method. It checks to see if the data is either null or has a length of zero.
     *
     * @param <code>data</code>The data to be checked.
     * @@return - TRUE if null or length<1, FALSE if not null
     */
    public static boolean isNorE(String data) {
        return (data == null) || (data.length() < 1);
    }

    /**
     * This is the static verification method. It checks to see if the data is either null or has only blank characters such as empty spaces, carriage returns, etc...
     *
     * @param <code>data</code>The data to be checked.
     * @@return - TRUE if null or length<1, FALSE if not null
     */
    public static boolean isNorEorB(String data) {
        return (data == null) || (data.trim().length() < 1);
    }

    /**
     * This is the static verification method. It checks to see if the data is either null or has a length of zero.
     *
     * @param <code>data</code>The data to be checked.
     * @return - TRUE if <code>not</code> null or length>0, FALSE if null or length<1
     */
    public static boolean isNotNorE(String data) {
        return (data != null) && (data.length() >= 1) && !data.equals("null");
    }

    /* *********************************************************************** */
    /* *************** VERIFY NULL OR EMPUT FOR STRINGBUFFERS *************** */

    /**
     * This is the static verification method. It checks to see if the data is either null or has a length of zero.
     *
     * @param <code>data</code>The data to be checked.
     * @@return - TRUE if null or length<1, FALSE if not null
     */
    public static boolean isNorE(StringBuffer data) {
        return (data == null) || (data.length() < 1);
    }

    /**
     * This is the static verification method. It checks to see if the data is either null or has a length of zero.
     *
     * @param <code>data</code>The data to be checked.
     * @return - TRUE if <code>not</code> null or length>0, FALSE if null or length<1
     */
    public static boolean isNotNorE(StringBuffer data) {
        return (data != null) && (data.length() >= 1);
    }

    public static String getMemoryStamp() {
        Runtime rt = java.lang.Runtime.getRuntime();
        long free = rt.freeMemory();
        long total = rt.totalMemory();

        DecimalFormat formatter = new DecimalFormat("###,###");
        String strfree = formatter.format(free / 1000) + "k";
        String strtotal = formatter.format(total / 1000) + "k";

        String msg = "JavaVM Memory: Free: " + strfree + " of Total: " + strtotal;
        return msg;
    }

    public static String getUnique5() {
        java.util.Date today = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ssSSS");
        String strTime = formatter.format(today);
        return strTime;
    }

    /* *********************************************************************** */
    /* ************************* trimString ******************************** */

    /**
     * This is a static method to remove spaces from both ends of a string.
     *
     * @param <code>data</code>The string to be trimed.
     * @@return - string with " " removed from both ends.
     */
    public static String trimString(String data) {
        // early exit on
        if (UTUtils.isNorE(data)) {
            return "";
        }

        char[] characters = data.toCharArray();

        int begChar = -1;
        int endChar = -1;

        // Cycle through the array of characters.
        // Bail at the first sight of a non-space char
        for (int i = 0; i < characters.length; i++) {
            if (!Character.isSpaceChar(characters[i])) {
                begChar = i;
                break;
            }
        }

        // Cycle through the array of characters BACKWARD.
        // Bail at the first sight of a non-space char
        for (int i = characters.length - 1; i >= 0; i--) {
            if (!Character.isSpaceChar(characters[i])) {
                endChar = i;
                break;
            }
        }

        return data.substring(begChar, endChar + 1);
    }

    /* *********************************************************************** */
    /* ****************** NAME/VALUE STRING TO HASHTABLE ******************** */

    /**
     * This is the static verification method. It checks to see if the data is either null or has a length of zero.
     *
     * @param <code>data</code>The data to be checked.
     * @@return - TRUE if null or length<1, FALSE if not null
     */
    public static Hashtable nvtoHash(String data) {
        return UTUtils.nvtoHash(data, "^", "=");
    }

    /**
     * This is the static verification method. It checks to see if the data is either null or has a length of zero.
     *
     * @param <code>data</code>The data to be checked.
     * @@return - TRUE if null or length<1, FALSE if not null
     */
    public static Hashtable nvtoHash(String data, String StrDelim, String StrCoupl) {

        boolean debug = false;

        if (debug) {
            System.out.println("data     :[" + data + "]");
        }
        if (debug) {
            System.out.println("StrDelim :[" + StrDelim + "]");
        }
        if (debug) {
            System.out.println("StrCoupl :[" + StrCoupl + "]\n\n");

            // Declarations
        }
        String NVpair;
        String StrThisName;
        String StrThisValue;
        Hashtable hshNew = new Hashtable();

        boolean bNotDone = false;
        boolean bLastLoop = false;
        int intNVCoupl;
        int intCkCoupl;
        int intCkStart;
        int intCkTail;

        // Since a delimiter can be more than one char, get the exact number
        int intSizeDelim = StrDelim.length();
        int intSizeCoupl = StrCoupl.length();

        intCkStart = 0;
        intCkCoupl = data.indexOf(StrCoupl);

        // Loop through the string, looking for n/v pairs. When you find one, put it in the hashtable
        while (intCkCoupl != -1) {
            if (debug) {
                System.out.println("\n\nLoop");

                // Search for the delimiter, if -1 then grab to the end of string
            }
            intCkTail = data.indexOf(StrDelim, intCkStart);
            if (intCkTail != -1) {
                NVpair = data.substring(intCkStart, intCkTail);
            } else {
                NVpair = data.substring(intCkStart);
            }
            NVpair.trim();
            if (debug) {
                System.out.println("NVpair :[" + NVpair + "]");

                // Get the coupler location (=), use it to get the name and value data
            }
            intNVCoupl = NVpair.indexOf(StrCoupl);
            StrThisName = NVpair.substring(0, intNVCoupl);
            StrThisValue = NVpair.substring(intNVCoupl + intSizeCoupl);
            if (debug) {
                System.out.println("intNVCoupl   :[" + intNVCoupl + "]");
            }
            if (debug) {
                System.out.println("StrThisName  :[" + StrThisName + "]");
            }
            if (debug) {
                System.out.println("StrThisValue :[" + StrThisValue + "]");

                // Add it to the hashtable
            }
            hshNew.put(StrThisName, StrThisValue);

            // Look for the next cookie, but start past the last one!
            if (intCkTail != -1) {
                intCkStart = intCkTail + intSizeDelim;
                intCkCoupl = data.indexOf(StrCoupl, intCkStart);
            } else {
                intCkCoupl = -1;
            }

        } // End While
        return hshNew;
    }

    /**
     * Cuts all whitespace characters out of a string. This is useful if you are trying to get rid of whitespace characters that are messing up things
     *
     * @param data
     * @return
     */
    public static String cutWhitespaceChars(String data) {
        // setup variables
        char[] charData;
        StringBuffer out = new StringBuffer();
        boolean match = false;
        int i, j;

        // if it is empty, do nothing
        if ((data.length() < 1)) {
            return data;
        }

        // convert stringbuffer to a char-array
        charData = new char[data.length()];
        data.getChars(0, data.length(), charData, 0);

        // loop through the char array
        for (i = 0; i < charData.length; i++) {
            char c = charData[i];
            if (!Character.isWhitespace(c)) {
                out.append(c);
            }
        }

        // make sure that the last chars are added
        out.append(data.substring(i));

        // return
        return out.toString();
    }

    /**
     * Cuts all control characters out of a string. This is useful if you are trying to get rid of control characters that are messing up things
     *
     * @param data
     * @return
     */
    public static String cutControlChars(String data) {
        // setup variables
        char[] charData;
        StringBuffer out = new StringBuffer();
        boolean match = false;
        int i, j;

        // if it is empty, do nothing
        if ((data.length() < 1)) {
            return data;
        }

        // convert stringbuffer to a char-array
        charData = new char[data.length()];
        data.getChars(0, data.length(), charData, 0);

        // loop through the char array
        for (i = 0; i < charData.length; i++) {
            char c = charData[i];
            if (!Character.isISOControl(c)) {
                out.append(c);
            }
        }

        // make sure that the last chars are added
        out.append(data.substring(i));

        // return
        return out.toString();
    }

    public static final String printTime(long start, long stop) {
        String stb = "";
        if (stop > 0 && start > 0) {
            long resulter = (stop - start);
            return printTime(resulter);
        }
        return stb;
    }

    public static final String printTime(long elapsedTimeMillis) {
        StringBuffer stb = new StringBuffer();
        long seconds = elapsedTimeMillis / 1000;
        long minutes = seconds / 60;

        long hours = 0;
        if (minutes > 60) {
            hours = minutes / 60;
            minutes = minutes % 60;
        }

        stb.append("      TIME REPORT:\n");
        // stb.append("=============================");
        stb.append("  hours  [" + hours + "]");
        stb.append("  minutes [" + minutes + "]");

        if (minutes == 0)
            stb.append("  seconds [" + seconds + "]");
        // stb.append("=============================");
        stb.append("\n");
        return stb.toString();
    }
}
