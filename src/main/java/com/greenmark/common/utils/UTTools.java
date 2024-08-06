package com.greenmark.common.utils;

import java.util.*;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTTools</p>
 * <p>Description: This class is currently not used.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTTools {
    public static final String CLASSNAME = "UTTools";

    public static final String DEFAULT_REPLACE = "/";

    /////////////////////////////////////////////////
    // METHODS
    /////////////////////////////////////////////////
    public static final String replaceSlashes(String data) {
        return replaceSlashes(data, UTTools.DEFAULT_REPLACE);
    }

    public static final String replaceSlashes(String data, String replace) {
        StringBuffer stb = new StringBuffer(data.length());

        StringTokenizer st = new StringTokenizer(data, "/");
        boolean first = true;

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (first) {
                stb.append(token);
                first = false;
            } else {
                stb.append(replace + token);
            }
        }

        return stb.toString();
    }

    public static final String prettyOutput(Hashtable h, String endline) {
        StringBuffer stb = new StringBuffer();
        for (Enumeration e = h.keys(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            String valu = (String) h.get(name);
            stb.append("name [" + name + "]  valu [" + valu + "]" + endline);
        }
        return stb.toString();
    }

    public static final String prettyOutput(SortedMap h, String endline) {
        StringBuffer stb = new StringBuffer();

        Iterator cc = h.values().iterator();
        while (cc.hasNext()) {
            String name = (String) cc.next();
            String valu = (String) h.get(name);
            stb.append("name [" + name + "]  valu [" + valu + "]" + endline);
        }
        return stb.toString();
    }
}
