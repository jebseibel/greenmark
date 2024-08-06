package com.greenmark.common.utils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTTextChecker</p>
 * <p>Description: This class is currently not used.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class UTTextChecker {
    public static final String CLASSNAME = "UTTextChecker";

    /////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////
    private UTTextChecker() {
        // can't call
    }

    /////////////////////////////////////////////////
    // STATIC METHODS
    /////////////////////////////////////////////////

    public static boolean checkText(String data) {
        // setup variables
        char[] charData;
        boolean match = true;

        int lenData = data.length();
        int i;
        // System.out.println( "len of incoming string data [" + lenData + "]" );

        // if it is empty, do nothing
        if ((lenData < 1)) {
            return match;
        }

        // convert stringbuffer to a char-array
        charData = new char[lenData];
        data.getChars(0, lenData, charData, 0);

        // loop through the char array
        for (i = 0; i < charData.length; i++) {
            char c = charData[i];
            if (c > '~') {
                System.out.println("Bad!!! position [" + i + "] data " + data.substring(i - 20, i));

                match = false;
            }
        }

        // return
        return match;
    }

}
