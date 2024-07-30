package com.greenmark.utils;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTQueryString</p>
 * <p>Description: This class is a utility class that does all the odds and ends that aren't handled
 * anywhere else. Specifically, it puts all the necessary ancillary tools in one class
 * so other packages don't have to be handed around with Nexus.</p>
 * 
 * NOTE:  CURRENTLY NOT USED.
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTQueryString {
	public static final String CLASSNAME = "UTQueryString";

	public static final String MULTI_VALUE_DELIMITER = ", ";

	/////////////////////////////////////////////////
	// CONSTRUCTORS
	/////////////////////////////////////////////////
	private UTQueryString() {
		// can't call
	}

	/////////////////////////////////////////////////
	// STATIC METHODS
	/////////////////////////////////////////////////

	public static Hashtable getValues(String queryString) {
		return UTQueryString.getValues(queryString, UTQueryString.MULTI_VALUE_DELIMITER);
	}

	/**
	 * This method returns a query string.
	 */
	public static Hashtable getValues(String queryString, String delim) {
		Hashtable hqs = new Hashtable();
		try {

			StringTokenizer st = new StringTokenizer(queryString, "&");

			// do first one
			String token = st.nextToken();
			int delim_loc = token.indexOf("=");
			String names = token.substring(0, delim_loc);
			String name = UTQueryString._buildValue(names).toString();
			String values = token.substring(delim_loc + 1);
			String value = UTQueryString._buildValue(values).toString();
			hqs.put(name, value);

			// get the rest of the tokens
			while (st.hasMoreTokens()) {
				token = st.nextToken();

				if (!token.endsWith("=")) {
					delim_loc = token.indexOf("=");

					names = token.substring(0, delim_loc);
					name = UTQueryString._buildValue(names).toString();

					values = token.substring(delim_loc + 1);
					value = UTQueryString._buildValue(values).toString();

					// check for multipart query items
					if (hqs.containsKey(name)) {
						String oldvalue = (String) hqs.get(name);
						value = oldvalue + delim + value;
					}

					hqs.put(name, value);
				}
			}
		} catch (Exception e) {
			System.out.println("Caught exception in CLASS: " + CLASSNAME + ", message: [" + e.getMessage() + "]");
		}
		return hqs;

	}

	/**
	 * build the value.
	 */
	private static StringBuffer _buildValue(String values) {
		StringBuffer out = new StringBuffer();
		String value = "";

		// early exit
		if (UTUtils.isNorE(values)) {
			return out;
		}

		// parse
		StringTokenizer st = new StringTokenizer(values, "+");
		value = st.nextToken();
		out.append(value);

		// get the rest
		while (st.hasMoreTokens()) {
			value = st.nextToken();
			out.append(" " + value);
		}

		return out;
	}

	/**
	 * Unpacks a multi value query string value and returns a vector of values. Use this if you had this class build you multivalue values. This happens when you use check boxes in a form - or have
	 * selects that can return more than one value.
	 * <p>
	 * 
	 * @param <code>values</code>
	 *            A string that has multivalues in it.
	 * @param <code>delim</code>
	 *            The delimiter between the values
	 */
	public static Vector unpackMultiValues(String values) {
		return UTQueryString.unpackMultiValues(values, UTQueryString.MULTI_VALUE_DELIMITER);
	}

	/**
	 * Unpacks a multi value query string value and returns a vector of values. Use this if you had this class build you multivalue values. This happens when you use check boxes in a form - or have
	 * selects that can return more than one value.
	 * <p>
	 * This method uses the UTQueryString.MULTI_VALUE_DELIMITER as the delimiter.
	 * <p>
	 * 
	 * @param <code>values</code>
	 *            A string that has multivalues in it.
	 * @param <code>delim</code>
	 *            The delimiter between the values
	 */
	public static Vector unpackMultiValues(String values, String delim) {
		Vector out = new Vector();

		// parse
		StringTokenizer st = new StringTokenizer(values, delim);
		String value = st.nextToken();
		out.addElement(value);

		// get the rest
		while (st.hasMoreTokens()) {
			value = st.nextToken();
			out.addElement(value);
		}

		return out;
	}

}
