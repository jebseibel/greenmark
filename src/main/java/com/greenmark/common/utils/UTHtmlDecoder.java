package com.greenmark.common.utils;

import java.nio.charset.StandardCharsets;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTHtmlDecoder</p>
 * <p>Description: The class contains a utility method for converting HTML entities
 * "&lt;", "&gt;", "&amp;" and "&quot;" in a <code>String</code> to
 * the characters '<', '>', '&' and '"' respectively. It does not
 * support any other HTML entities.
 * 
 *  Currently NOT USED.
 *
 *  This is modeled after URLDecoder.java source from JDK1.2</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTHtmlDecoder {
    public static final String CLASSNAME = "UTHtmlDecoder";

    /**
     * You can't call the constructor.
     */
    private UTHtmlDecoder() {
    }

    /**
     * Translates HTML entities to characters
     *
     * @param s <code>String</code> to be translated.
     * @return the translated <code>String</code>.
     */
    public static String decode(String s) throws Exception {
        StringBuffer sb = new StringBuffer(s.length());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '&':
                    try {
                        if (s.startsWith("lt;", i + 1)) {
                            sb.append('<');
                            i += 3;
                        } else if (s.startsWith("gt;", i + 1)) {
                            sb.append('>');
                            i += 3;
                        } else if (s.startsWith("amp;", i + 1)) {
                            sb.append('&');
                            i += 4;
                        } else if (s.substring(i + 1, i + 7).equals("quot;")) {
                            sb.append('"');
                            i += 6;
                        } else {
                            sb.append(c);
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                default:
                    sb.append(c);
            }
        }
        // Undo conversion to external encoding
        String result = sb.toString();
        byte[] inputBytes = result.getBytes(StandardCharsets.ISO_8859_1);
        return new String(inputBytes);
    }

}
