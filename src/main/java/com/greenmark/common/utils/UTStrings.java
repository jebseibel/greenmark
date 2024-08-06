package com.greenmark.common.utils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTStrings {
    public static final String CLASSNAME = "UTStrings";

    public static String getBooleanStringYN(boolean input) {
        if (input)
            return "Y";
        else
            return "N";
    }
}
