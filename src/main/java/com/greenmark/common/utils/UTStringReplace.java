package com.greenmark.common.utils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTStringReplace</p>
 * <p>Description: Replaces variables in specified String</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTStringReplace {
    public static final String CLASSNAME = "UTStringReplace";

    /////////////////////////////////////////////////
    // PUBLIC METHODS
    /////////////////////////////////////////////////

    /**
     * Performs an individual subsitution with provided replace values.
     */
    public static StringBuffer replace(String token, String replacement, String data) {

        return UTStringReplace._replace(token, replacement, new StringBuffer(data));
    }

    //////////////////////////////
    // This is the core method that does the actual replacement.
    //
    public static StringBuffer replace(String token, String replacement, StringBuffer data) {
        return UTStringReplace._replace(token, replacement, data);
    }

    //////////////////////////////
    // This is the core method that does the actual replacement.
    //
    private static StringBuffer _replace(String token, String replacement, StringBuffer data) {
        // setup variables
        char[] charData;
        char[] charTok;
        char[] charDone;
        StringBuffer out = new StringBuffer();
        boolean match = false;
        int i, j;

        // if it is empty, do nothing
        if ((data.length() < 1) || (token.length() < 1) || (replacement.length() < 1)) {
            return data;
        }

        // convert stringbuffer to a char-array
        charData = new char[data.length()];
        data.getChars(0, data.length(), charData, 0);

        charTok = new char[token.length()];
        token.getChars(0, token.length(), charTok, 0);

        // we'll be matching for the last character of the token string.
        // get the last char from the token to match with
        char charmatch = charTok[0];
        int lenmatch = charTok.length;
        int lendata = charData.length;

        // check and see if any match
        int thislen = charData.length - (lenmatch - 1);

        // loop through the char array
        for (i = 0; i < thislen; i++) {
            match = false;

            // if we find a match - try to match the whole token
            if (charmatch == charData[i]) {
                // System.out.println(". . . match! first - try others.");
                // walk the others die as soon as possible
                match = true;
                for (j = 0; j < lenmatch; j++) {
                    // if no match break out
                    if (charData[i + j] != charTok[j]) {
                        // System.out.println(". . . charData[i+j] [" + charData[i+j] + "] charTok[j] [" + charTok[j] + "]. . . no match break out");
                        match = false;
                        break;
                    }
                    // System.out.println(". . . charData[i+j] [" + charData[i+j] + "] charTok[j] [" + charTok[j] + "]. . . match-continue");
                }

                // clean up
                if (match) {
                    // System.out.println(". . . found a match!!");
                    out.append(replacement);
                    i = i + lenmatch - 1;

                    // exit if this is the end of the array
                    if (i == lendata) {
                        break;
                    }
                }
            }

            // append the char to the output only if not matching
            if (!match) {
                out.append(charData[i]);
            }

        }

        // System.out.println("match [" + match + "]");

        // make sure that the last chars are added
        out.append(data.substring(i));

        // return
        return out;
    }

    private static StringBuffer cutControlChars(StringBuffer data, char[] exclude) {
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
            // System.out.println("charData[i] ["+ charData[i] +"]" );

            // if we find a match do the second test
            // we have staggered them this way so we are not testing each char
            // twice. That alone will improve the speed.
            if (Character.isISOControl(c)) {
                if (UTStringReplace.isExcluded(exclude, c)) {
                    out.append(c);
                }
            } else {
                out.append(c);
            }
        }

        // make sure that the last chars are added
        out.append(data.substring(i));

        // return
        return out;
    }

    public static final boolean isExcluded(char[] exclude, char c) {
        for (int i = 0; i < exclude.length; i++) {
            char x = exclude[i];
            if (x == c) {
                return true;
            }
        }

        // if it isnt in the excluded list, return false
        System.out.println("Not excluded [" + c + "]");
        return false;
    }

} // end class