package com.greenmark.utils;

import java.util.BitSet;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTHtmlEncoder</p>
 * <p>Description: The class contains a utility method for converting the characters
 * '<', '>', '&' and '"' in a <code>String</code> to the HTML entities
 * "&lt;", "&gt;", "&amp;" and "&quot;" respectively. It does not
 * support any other entities.
 *
 *  Currently NOT USED.
 *  
 *  This is modeled after URLEncoder.java source from JDK1.2</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTHtmlEncoder {
	public static final String CLASSNAME = "UTHtmlEncoder";

	static BitSet needEncoding;

	static {
		needEncoding = new BitSet(256);

		needEncoding.set('<');
		needEncoding.set('>');
		needEncoding.set('&');
		needEncoding.set('"');
	}

	/**
	 * You can't call the constructor.
	 */
	private UTHtmlEncoder() {
	}

	/**
	 * Translates a string into HTML entities.
	 *
	 * @param s
	 *            <code>String</code> to be translated.
	 * @return the translated <code>String</code>.
	 */
	public static String encode(String s) {
		StringBuffer out = new StringBuffer(s.length());

		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i);

			switch (c) {
			case '<':
				out.append("&lt;");
				break;
			case '>':
				out.append("&gt;");
				break;
			case '&':
				out.append("&amp;");
				break;
			case '"':
				out.append("&quot;");
				break;
			default:
				out.append((char) c);
			}
		}

		return out.toString();
	}

}
