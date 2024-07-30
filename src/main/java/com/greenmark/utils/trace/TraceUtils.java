package com.greenmark.utils.trace;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: TraceUtils</p>
 * <p>Description: This class is a utility class that does all the odds and ends that aren't handled
 * anywhere else. Specifically, it puts all the necessary ancillary tools in one class
 * so other packages don't have to be handed around with Nexus.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class TraceUtils {
	public static final String CLASSNAME = "TraceUtils";

	/////////////////////////////////////////////////
	// CONSTRUCTORS
	/////////////////////////////////////////////////
	private TraceUtils() {
		// can't call
	}

	/////////////////////////////////////////////////
	// STATIC METHODS
	/////////////////////////////////////////////////
	/* *********************************************************************** */
	/* ********************** VERIFY NULL FOR OBJECTS *********************** */
	/**
	 * This is the static verification method. It checks to see if the object is null.
	 *
	 * @param <code>data</code>The
	 *            object to be checked.
	 * @@return - TRUE if null, FALSE if not null
	 */
	public static boolean isNull(Object data) {
        return data == null;
	}

	/**
	 * This is the static verification method. It checks to see if the object is not null.
	 *
	 * @param <code>data</code>The
	 *            object to be checked.
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
	 * @param <code>data</code>The
	 *            data to be checked.
	 * @@return - TRUE if null or length<1, FALSE if not null
	 */
	public static boolean isNorE(String data) {
        return (data == null) || (data.length() < 1);
	}

	/**
	 * This is the static verification method. It checks to see if the data is either null or has a length of zero.
	 *
	 * @param <code>data</code>The
	 *            data to be checked.
	 * @return - TRUE if <code>not</code> null or length>0, FALSE if null or length<1
	 */
	public static boolean isNotNorE(String data) {
        return (data != null) && (data.length() >= 1);
	}

}
