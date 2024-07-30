package com.greenmark.utils.dv;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DVImportXml</p>
 * <p>Description: This data vault utility class contains convenience methods for the XML data that is fed into a Datavault object (strategy.xml)</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DVImportJson {
	public static final String CLASSNAME = "DVImportXml";

	private static final char LT = '<';
	private static final char RT = '>';
	private static final char END = '/';

	private static final int INITIAL_VECTOR_SIZE = 1000;
	private static final int VECTOR_INCREMENT = 100;

	private static final char COLLECTION_DELIMITER = '~';

	private static final int INTAG = 0;
	private static final int OUTTAG = 1;
	private static final int EXITINGTAG = 2;
	private static final int ENTERINGTAG = 3;
	private static final int STARTINGTAG = 4;

	private static final int Start = 0;
	private static final int InTag = 1;
	private static final int OutTag = 2;

	/////////////////////////////////////////////////
	// Constructors
	/////////////////////////////////////////////////

	// no constructor, this is a static class

	/////////////////////////////////////////////////
	// Public Methods
	/////////////////////////////////////////////////

	public static final void extract(StringBuffer xml, DVDatavault dv, String dataVaultTag, StringBuffer collectionDelimiter) {
		// parse the xml
		Vector v = _parse(xml, INITIAL_VECTOR_SIZE);
		// process the xml
		dv = _processVector(dv, v, collectionDelimiter, new DVDatavaultTag(dataVaultTag));
	}

	public static final void extract(StringBuffer xml, DVDatavault dv, String dataVaultTag) {
		String col = Character.toString(DVImportJson.COLLECTION_DELIMITER);
		DVImportJson.extract(xml, dv, dataVaultTag, new StringBuffer(col));
	}

	public static final void extract(String xml, DVDatavault dv, String dataVaultTag) {
		StringBuffer stb = new StringBuffer(xml);
		String col = Character.toString(DVImportJson.COLLECTION_DELIMITER);
		DVImportJson.extract(stb, dv, dataVaultTag, new StringBuffer(col));
	}

	public static final DVDatavault extract(String xml) {
		String dataVaultTag = "DV:XML";
		DVDatavault dv = new DVDatavault();
		StringBuffer stb = new StringBuffer(xml);
		String col = Character.toString(DVImportJson.COLLECTION_DELIMITER);
		DVImportJson.extract(stb, dv, dataVaultTag, new StringBuffer(col));
		return dv;
	}

	/////////////////////////////////////////////////
	// Private Methods
	/////////////////////////////////////////////////

	//
	// parses out a vector of in and out items
	//
	private static final Vector _parse(StringBuffer xml, int vectorSize) {
		char cur;
		int mode = DVImportJson.OutTag;
		Vector v = new Vector(vectorSize, VECTOR_INCREMENT);
		StringBuffer sb = new StringBuffer();

		char[] cc = new char[xml.length()];
		xml.getChars(0, xml.length(), cc, 0);

		for (int i = 0; i < cc.length; i++) {
			cur = cc[i];
			switch (mode) {
			case DVImportJson.InTag:
				if (cur == RT) {
					mode = DVImportJson.OutTag;
					v.add(sb);
					sb = new StringBuffer();
				} else {
					sb.append(cur);
				}
				break;
			case DVImportJson.OutTag:
				if (cur == LT) {
					mode = DVImportJson.InTag;
					v.add(sb);
					sb = new StringBuffer();
				} else {
					sb.append(cur);
				}
				break;
			}
		}
		return (v);
	}

	//
	// Processes the parsed vector extracting the data out to the DVDatavault
	//
	private static final DVDatavault _processVector(DVDatavault dv, Vector v, StringBuffer collectionDelimiter, DVDatavaultTag dvt) {
		StringBuffer item;
		StringBuffer out = new StringBuffer();
		int mode = OutTag;
		for (Enumeration e = v.elements(); e.hasMoreElements();) {
			item = (StringBuffer) e.nextElement();
			switch (mode) {
			case DVImportJson.OutTag:
				mode = DVImportJson.InTag;
				String insert = item.toString();
				if (item.length() > 0) {
					if (!(item.length() < 2 && item.charAt(0) == '\n')) {
						_pushIntoDVDatavault(null, item, dvt, dv);
					}
				}
				item = new StringBuffer();
				break;
			case DVImportJson.InTag:
				mode = DVImportJson.OutTag;
				_parseInsideTheTag(item, dvt, dv, collectionDelimiter);
				out.append("IN:  " + item.toString() + "\n");
				break;
			}
		}
		return (dv);
	}

	//
	// grabs a StringBuffer and parses it into a vector based on a char.
	//
	private static final Vector _parseIntoVector(StringBuffer sb, char delim) {
		char[] cc = new char[sb.length()];
		sb.getChars(0, sb.length(), cc, 0);

		StringBuffer it = new StringBuffer();
		Vector v = new Vector();
		for (int i = 0; i < cc.length; i++) {
			if (cc[i] == delim) {
				v.add(it);
				it = new StringBuffer();
			} else if (cc[i] == '"') {
				i = _extractUntilDelimOrBoundary(cc, i, '"', '"', it);
				v.add(it);
				it = new StringBuffer();
			} else {
				it.append(cc[i]);
			}
		}
		v.add(it);
		return (v);
	}

	//
	// parses out a name/value pair based on the specified delimiter
	//
	private static final Vector _parsePairs(StringBuffer sb, char delim) {
		char[] cc = new char[sb.length()];
		sb.getChars(0, sb.length(), cc, 0);
		StringBuffer name = new StringBuffer();
		StringBuffer value = new StringBuffer();
		Vector v = new Vector(2);
		boolean delimFound = false;

		for (int i = 0; i < cc.length; i++) {
			if (delimFound) {
				value.append(cc[i]);
			} else {
				if (cc[i] == delim) {
					delimFound = true;
					continue;
				}
				name.append(cc[i]);
			}
		}
		v.add(name);
		v.add(value);
		return (v);
	}

	//
	// parses out the "inside" xml tag.
	//
	private static final void _parseInsideTheTag(StringBuffer sb, DVDatavaultTag dvt, DVDatavault dv, StringBuffer collectionDelimiter) {
		// setup
		StringBuffer xmlTag = new StringBuffer();
		String item;
		Vector tokens;
		int size;
		boolean shouldWePop = false;

		// parse the inbound string into a vector delimited by spaces.
		tokens = _parseIntoVector(sb, ' ');
		size = tokens.size();

		// If the size is greater than zero then pull the first element out and
		// determine if we shoud push or pop the DVDatavaultTag.
		if (size > 0) {
			xmlTag = (StringBuffer) tokens.elementAt(0);
			if (xmlTag.charAt(0) == END) {
				// pop off an element from the DVDatavaultTag
				dvt.pop();
				return;
			} else {
				if (size < 2) {
					// push a new element into the DVDatavaultTag
					dvt.push(xmlTag.toString());
					return;
				}
			}
		}
		// If the size of the tokens vector is greater than 1, then we have
		// properties (pairs) to parse.
		if (size > 1) {
			Vector pairs = new Vector();
			Hashtable nvPairs = new Hashtable();
			// loop through all the pairs returned and process.
			for (int i = 1; i < size; i++) {
				// get the vector of pairs
				pairs = _parsePairs((StringBuffer) tokens.elementAt(i), '=');
				nvPairs.put(pairs.elementAt(0).toString(), pairs.elementAt(1));
			}
			// update the xmlTag based on "name" or "id"
			if (collectionDelimiter != null && nvPairs.containsKey(collectionDelimiter.toString())) {
				xmlTag.append(COLLECTION_DELIMITER);
				xmlTag.append(nvPairs.get(collectionDelimiter.toString()));
			}
			// push the next DVDatavaultTag segment
			dvt.push(xmlTag.toString());
			// push the properties
			for (Enumeration e = nvPairs.keys(); e.hasMoreElements();) {
				item = (String) e.nextElement();
				if (item.equals("/")) {
					// because we use a Hashtable the order of "/" is not
					// guaranteed. So we set a property and then act on it
					// after the loop.
					shouldWePop = true;
				} else {
					// good to go, insert into the DVDatavault
					_pushIntoDVDatavault(item, nvPairs.get(item), dvt, dv);
				}
			}
			if (shouldWePop) {
				dvt.pop();
			}
		}
	}

	//
	// pushes an isolated value into the DVDatavault
	//
	private static final void _pushIntoDVDatavault(Object name, Object value, DVDatavaultTag dvt, DVDatavault dv) {
		String newValue = value.toString().trim();
		if (!newValue.equals("")) {
			if (name != null) {
				dvt.push(name.toString());
				dv.set(dvt, newValue);
				dvt.pop();
			} else {
				dv.set(dvt, newValue);
			}
		}
	}

	//
	// extracts a char to a delimiter OR a boundary
	//
	private static int _extractUntilDelimOrBoundary(char[] cc, int start, char delim, char boundary, StringBuffer sb) {
		char curr;
		int i = start;
		if (cc[i] == delim) {
			i++;
		}
		for (i = i; i < cc.length; i++) {
			curr = cc[i];
			if (curr == delim || curr == boundary) {
				break;
			}
			sb.append(curr);
		}
		return (i);
	}

}
