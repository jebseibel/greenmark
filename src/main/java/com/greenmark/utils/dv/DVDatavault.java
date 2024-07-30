package com.greenmark.utils.dv;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DVDatavault</p>
 * <p>Description: The purpose of this class is the provide a single data storage object.  All
 * processing results are stored in one of the specified 'hash-of-hashes.'  Easy
 * access to any type of data object stored in this object makes it ideal for
 * use where you need to pass around a varied array of data.  Method calls become
 * trivial since you are passing in an entire dataset of varied objects.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DVDatavault {
	public static final String CLASSNAME = "DVDatavault";

	// class variables
	private Hashtable DATA;

	// class constants
	private static final int GET = 0;
	private static final int DEL = 1;

	// Class Maps
	public static final String CLASS_MAP_HASHTABLE = "java.util.Hashtable";
	public static final String CLASS_MAP_STRING = "java.lang.String";

	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * ************************************************************************ Creates a new DVDatavault. Generally speaking, you should use the DVDatavaultTag.create() method to create a new
	 * DVDatavault object. But, if you must, you can create one directly.
	 */
	public DVDatavault() {
		this.DATA = new Hashtable();
	}

	////////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * ************************************************************************ This method returns a reference to the entire "hash-of-hashes" stored inside the DVDatavault
	 * 
	 * @return the top level hashtable in this DVDatavault.
	 */
	public final Hashtable get() {
		return (this.DATA);
	}

	/**
	 * ************************************************************************ Returns a Vector of keys referenced by the DVDatavault Tag.
	 * 
	 * @param dvTag
	 *            The DVDatavault Tag pointer to the keys you wish to extract
	 * @return Vector
	 */
	public final Vector getKeysVector(String dvTag) {
		try {
			Hashtable ht = this.getHashtable(dvTag);
			Vector v = new Vector(ht.size());
			for (Enumeration en = ht.keys(); en.hasMoreElements();) {
				Object o = en.nextElement();
				v.add(o);
			}
			return (v);
		} catch (Exception ex) {
			return (new Vector(0));
		}
	}

	/**
	 * ************************************************************************ Returns a Vector of values referenced by the DVDatavault Tag.
	 * 
	 * @param dvTag
	 *            The DVDatavault Tag pointer to the values you wish to extract
	 * @return Vector
	 */
	public final Vector getValuesVector(String dvTag) {
		try {
			Hashtable ht = this.getHashtable(dvTag);
			Vector v = new Vector(ht.size());
			for (Enumeration en = ht.elements(); en.hasMoreElements();) {
				v.add(en.nextElement());
			}
			return (v);
		} catch (Exception ex) {
			return (new Vector(0));
		}
	}

	/**
	 * ************************************************************************ This method returns a single hashtable with the DVDatavaultTags as the keys, and the values as, you guessed it, the
	 * values.
	 * 
	 * @return this DVDatavault as a single hashtable.
	 */
	public final Hashtable getSingleHash() {
		return (this.getSingleHash(DVDatavaultTag.TAG_ID + DVDatavaultTag.TAG_DELIM));
	}

	/**
	 * ************************************************************************ This method returns a single hashtable with the DVDatavaultTags as the keys, and the values as, you guessed it, the
	 * values.
	 * 
	 * @param dvTagPrefix
	 *            - the DVDatavault prefix you wish to add to the output.
	 * @return the DVDatavault as a single hash
	 */
	public final Hashtable getSingleHash(String dvTagPrefix) {
		return (DVDatavaultFactory._extractSingleHashtable(this.DATA, new Hashtable(), dvTagPrefix, 0));
	}

	/**
	 * ************************************************************************ Clears all data.
	 */
	public final void clear() {
		this.DATA.clear();
	}

	/**
	 * ************************************************************************ Takes the data from a "flattened" DVDatavault and "inflates" it into this DVDatavault.
	 * 
	 * @param hash
	 */
	public final void insertSingleHash(Hashtable hash) {
		Object val, key;
		for (Enumeration en = hash.keys(); en.hasMoreElements();) {
			key = en.nextElement();
			val = hash.get(key);
			this.set(key.toString(), val);
		}
	}

	/**
	 * ************************************************************************ Retrieve an object stored in the DVDatavault. If nothing is found in the DVDatavault at the location of the
	 * DVDatavaultTag an empty String is return as an object.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag.
	 * @return gets the referenced objects.
	 */
	public final Object get(String tag) {
		try {
			Object o = (Object) DVDatavault._getOrDelete(this.DATA, tag, DVDatavault.GET);
			if (o != null) {
				return (o);
			}
		} catch (Exception e) {
		}
		return (new String(""));
	}

	/**
	 * ************************************************************************ Tests if some DVDatavault tag maps into the specified value in this DVDatavault.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag.
	 * @return true if the datavault contains the referenced object, or false if not.
	 */
	public final boolean contains(String tag) {
		try {
			Object o = (Object) DVDatavault._getOrDelete(this.DATA, tag, DVDatavault.GET);
			if (o != null) {
				return (true);
			}
		} catch (Exception e) {
		}
		return (false);
	}

	/**
	 * ************************************************************************ Retrieve a String. If the object is null or uncastable to String, then an empty String is returned.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag.
	 * @return a string representation of this object.
	 */
	public final String getString(String tag) {
		try {
			String s = (String) DVDatavault._getOrDelete(this.DATA, tag, DVDatavault.GET);
			if (s != null) {
				return (s);
			}
		} catch (Exception e) {
		}
		return (new String(""));
	}

	/**
	 * ************************************************************************ Retrieves a named http parameter from the location: "DV:HTTP.PARAM.[parameter]".
	 * 
	 * @param parameter
	 *            the HTTP parameter to get.
	 * @return the HTML parameter specified or an empty String.
	 */
	public final String getHttpParam(String parameter) {
		// build the datavault tag (properly)
		StringBuffer sb = new StringBuffer(60);
		sb.append(DVDatavaultTag.TAG_ID);
		sb.append(DVDatavaultTag.TAG_DELIM_CHAR);
		sb.append("HTTP");
		sb.append(DVDatavaultTag.LIST_DELIM_CHAR);
		sb.append("PARAM");
		sb.append(DVDatavaultTag.LIST_DELIM_CHAR);
		sb.append(parameter);
		// extract and return the parameter
		return (this.getString(sb.toString()));
	}

	/**
	 * ************************************************************************ Retrieve a String as an int. If the object is null or uncastable to int, then -1 is returned.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag.
	 * @return the value as an int, or -1 if the value cannot be converted to int.
	 */
	public final int getStringAsInt(String tag) {
		try {
			String s = (String) DVDatavault._getOrDelete(this.DATA, tag, DVDatavault.GET);
			int r = Integer.parseInt(s);
			return (r);
		} catch (Exception e) {
		}
		return (-1);
	}

	/**
	 * ************************************************************************ Retrieve a String as an float. If the object is null or uncastable to float, then -1 is returned.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag.
	 * @return the value as an int, or -1 if the value cannot be converted to int.
	 */
	public final float getStringAsFloat(String tag) {
		try {
			String s = (String) DVDatavault._getOrDelete(this.DATA, tag, DVDatavault.GET);
			float r = Float.parseFloat(s);
			return (r);
		} catch (Exception e) {
		}
		return (-1);
	}

	/**
	 * ************************************************************************ Retrieve a String as an double. If the object is null or uncastable to double, then -1 is returned.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag.
	 * @return the value as an int, or -1 if the value cannot be converted to int.
	 */
	public final double getStringAsDouble(String tag) {
		try {
			String s = (String) DVDatavault._getOrDelete(this.DATA, tag, DVDatavault.GET);
			double r = Double.parseDouble(s);
			return (r);
		} catch (Exception e) {
		}
		return (-1);
	}

	/**
	 * ************************************************************************ Retrieve a Hashtable. If the object is null or uncastable to Hashtable, then an empty Hashtable is returned.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag.
	 * @return extracts the hasthable referenced by the tag passed in. Returns an empty hashtable if the value referenced by the tag does not exist, or is not a hashtable
	 */
	public final Hashtable getHashtable(String tag) {
		try {
			Hashtable h = (Hashtable) DVDatavault._getOrDelete(this.DATA, tag, DVDatavault.GET);
			if (h != null) {
				return (h);
			}
		} catch (Exception e) {
		}
		return (new Hashtable());
	}

	/**
	 * ************************************************************************ Remove a stored object from the DVDatavault.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag.
	 * @return retrieves the object referenced by the tag, or an empty object if the value does not exist in the datavault.
	 */
	public final Object remove(String tag) {
		try {
			Object o = DVDatavault._getOrDelete(this.DATA, tag, DVDatavault.DEL);
			if (o != null) {
				return (o);
			}
		} catch (Exception e) {
		}
		return (new Object());
	}

	/**
	 * ************************************************************************ Merge this DVDatavault with another. WARNING: any colliding DVDatavault tags will be over-written with the new values
	 * passed in with the merging DVDatavault.
	 * 
	 * @param dv
	 *            DVDatavault The DVDatavault to merge.
	 */
	public final void merge(DVDatavault dv) {
		DVDatavault newDv = DVDatavaultFactory.merge(this, dv);
		this.DATA = newDv.get();
	}

	/**
	 * ************************************************************************ Merge this DVDatavault with another. Collisions are avoided by passing in a DVDatavault Tag root that will be applied to
	 * all merged values.
	 * 
	 * @param dv
	 *            the DVDatavault to merge.
	 * @param tagRoot
	 *            the root DVDatavault Tag to be applied to all values in the DVDatavault that is being merged in.
	 */
	public final void merge(DVDatavault dv, String tagRoot) {
		if (!tagRoot.endsWith(DVDatavaultTag.LIST_DELIM)) {
			tagRoot = tagRoot + DVDatavaultTag.LIST_DELIM;
		}
		Hashtable ht = dv.getSingleHash(tagRoot);
		for (Enumeration en = ht.keys(); en.hasMoreElements();) {
			Object key = en.nextElement();
			this.set(key.toString(), ht.get(key));
		}
	}

	/**
	 * ************************************************************************ Add an Object to the DVDatavault.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag.
	 * @param value
	 *            the Object that is to be stored.
	 * @return true it the set executed properly, false if not.
	 */
	public final boolean set(String tag, Object value) {
		System.out.println("tag"+tag);
		System.out.println("value"+value);
		return (DVDatavaultFactory._set(this.DATA, new DVDatavaultTag(tag), value));
	}

	/**
	 * ************************************************************************ Add an Object to the DVDatavault.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag Object.
	 * @param value
	 *            the Object that is to be stored.
	 * @return true it the set executed properly, false if not.
	 */
	public final boolean set(DVDatavaultTag tag, Object value) {
		return (DVDatavaultFactory._set(this.DATA, tag, value));
	}

	/**
	 * ************************************************************************ Add a Hashtable to the DVDatavault.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag tag.
	 * @param value
	 *            the Hashtable to be added.
	 * @return true it the set executed properly, false if not.
	 */
	public final boolean set(String tag, Hashtable value) {
		DVDatavaultFactory._addHashtable(this, value, tag);
		return (true);
	}

	/**
	 * ************************************************************************ Add a Hashtable to the DVDatavault.
	 * 
	 * @param tag
	 *            a DVDatavaultTag tag root where the imported xml will go.
	 * @param xml
	 *            the XML to import.
	 * @param collectionDelimiter
	 *            the inner tag element to use to distinguish a collection.
	 */
	// public final void importXml( String tag, StringBuffer xml, String collectionDelimiter )
	// {
	// DVImportXml.extract( xml, this, tag, new StringBuffer(collectionDelimiter) );
	// }

	/**
	 * ************************************************************************ Verifies that an Object exists in the DVDatavault at the tag specified.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag tag.
	 * @return true or false.
	 */
	public final boolean exists(String tag) {
		return (DVDatavault._exists(this, tag));
	}

	/**
	 * ************************************************************************ /** Return an XML version of the DataVault.
	 * 
	 * @return A StringBuffer containing the XML.
	 */
	public final StringBuffer toXml() {
		StringBuffer xml = new StringBuffer();
		DVDatavaultFactory.dumpXml(xml, this.DATA, "", "", "");
		return (xml);
	}

	/**
	 * ************************************************************************ /** Return an XML version of the DataVault.
	 * 
	 * @param tabelem
	 *            Tab element to add to output.
	 * @param endline
	 *            End of line element to add to output.
	 * @return A StringBuffer containing the XML.
	 */
	public final StringBuffer toXml(String tabelem, String endline) {
		StringBuffer xml = new StringBuffer();
		DVDatavaultFactory.dumpXml(xml, this.DATA, "", "", "", tabelem, endline);
		return (xml);
	}

	/**
	 * ************************************************************************ Return a dump of all data in the DVDatavault.
	 * 
	 * @return A StringBuffer containing the dump data.
	 */
	public final StringBuffer dump() {
		String start = new String(">START: DVDatavaultDump: all\n");
		String end = new String(">END: DVDatavaultDump: all\n");
		String prefix = new String(">\t");
		String suffix = new String("");
		return (DVDatavaultFactory.dump(this, start, end, prefix, suffix));
	}

	/**
	 * ************************************************************************ Return a dump of all data in the DVDatavault in HTML format.
	 * 
	 * @return A StringBuffer containing the dump data.
	 */
	public final StringBuffer dumpHTML() {
		String start = new String("<TABLE><TR><TD><B>START: DVDatavaultDump: all</B></TD></TR>\n");
		String end = new String("<TR><TD><B>END: DVDatavaultDump: all</B></TD></TR>\n</TABLE>\n");
		String prefix = new String("<TR><TD>&nbsp;&nbsp;&nbsp;");
		String suffix = new String("</TD></TR>");
		return (DVDatavaultFactory.dump(this, start, end, prefix, suffix));
	}

	/**
	 * ************************************************************************ Return a dump of all data in the DVDatavault starting at the specified tag.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag tag.
	 * @return A StringBuffer containing the dump data.
	 */
	public final StringBuffer dump(String tag) {
		String start = new String(">START: DVDatavaultDump: path at: " + tag + "\n");
		String end = new String(">END: DVDatavaultDump: path at: " + tag + "\n");
		String prefix = new String(">\t");
		String suffix = new String("");
		return (DVDatavaultFactory.dump(this, tag, start, end, prefix, suffix));
	}

	/**
	 * ************************************************************************ Return a dump of all data in the DVDatavault starting at the specified tag in HTML format.
	 * 
	 * @param tag
	 *            a valid DVDatavaultTag tag.
	 * @return A StringBuffer containing the dump data.
	 */
	public final StringBuffer dumpHTML(String tag) {
		String start = new String("<TABLE><TR><TD><B>START: DVDatavaultDump: path at: " + tag + "</B></TD></TR>\n");
		String end = new String("<TR><TD><B>END: DVDatavaultDump: path at: " + tag + "</B></TD></TR>\n</TABLE>\n");
		String prefix = new String("<TR><TD>&nbsp;&nbsp;&nbsp;");
		String suffix = new String("</TD></TR>");
		return (DVDatavaultFactory.dump(this, tag, start, end, prefix, suffix));
	}

	////////////////////////////////////////////////////////////////////////////////
	// PRIVATE METHODS
	////////////////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////////////
	// Verifies that a databag contains a value specified by a DVDatavaultTag
	//
	private static final boolean _exists(DVDatavault self, String item) {
		// setup
		Object test;

		// test DVDatavaultTag before proceeding
		if (DVDatavaultTag.isValid(item)) {
			// okay, passed the valid tag test, lets go further...
			try {
				test = DVDatavault._getOrDelete(self.DATA, item, DVDatavault.GET);
			} catch (Exception e) {
				// exception obviously means there's no value!!
				return (false);
			}

			// null!! then no value.
			if (test == null) {
				return (false);
			}
		} else {
			return (false);
		}

		// Well, none of the tests came back false. I guess by default the
		// answer is true!
		return (true);
	}

	/////////////////////////////////////////////////////////////////////////////
	// Gets or deletes a value from the DVDatavault
	//
	private static final Object _getOrDelete(Hashtable ht, String item, int opr) {
		// setup
		DVDatavaultTag tag = new DVDatavaultTag(item);
		Vector keys = tag.getPath();

		// process
		if (!tag.isValid()) {
			// return an empty string because if we returned null it might
			// cause problems if programmers don't properly trap for null
			// values (tried it and it broke, so we'll do it this way
			// until someone comes up with a better way.
			return (new String());
		} else if (keys == null) { // at root level, return entire hashtable
			return (ht);
		} else { // find hashtable
			Hashtable curr = ht;
			String last = (String) keys.remove(keys.size() - 1); // extract the last element for the hashtable 'put' method.
			curr = DVDatavaultFactory._findHashtable(ht, keys);

			try {
				// process details depending on case
				Object ret;
				switch (opr) {
				case DVDatavault.GET:
					ret = curr.get(last);
					break;
				case DVDatavault.DEL:
					ret = curr.remove(last);
					break;
				default:
					ret = null;
					break;
				}
				return (ret);
			} catch (Exception e) {
				// If we exception out of the previous code then neither the
				// get or remove will work because the tag passed in is valid
				// but the value doesn't exist in the hashtable in question.
				// Null is returned because, well, I guess, null is the answer.
				return (null);
			}
		}
	}

}
