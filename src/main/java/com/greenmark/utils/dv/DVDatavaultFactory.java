package com.greenmark.utils.dv;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import com.greenmark.utils.UTDisplayFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DVDatavaultFactory</p>
 * <p>Description:  DVDatavaultFactory is used to create and manage (to a certain degree) a DVDatavault.
 * Proper creation of a DVDatavault can only come through this factory.  It also
 * contains certain manipulator code for DVDatavaults such as "merge."  This code
 * is placed here instead of the DVDatavault to keep the overhead-size of a
 * DVDatavault as small as possible.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DVDatavaultFactory {
	public static final String CLASSNAME = "DVDatavaultFactory";

	// Class Maps
	public static final String CLASS_MAP_HASHTABLE = "java.util.Hashtable";
	public static final String CLASS_MAP_STRING = "java.lang.String";

	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	// DVDatavaultFactory doesn't have a constructor because it doesn't have an
	// instance. This is due to the fact that it contains all the static
	// methods and global constants used in the DVDatavault. This design
	// enables DVDatavault to be extremely light-weight, as it should be.

	////////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////
	// DVDatavault Constructor Methods
	////////////////////////////////////////////////////////////////////////////

	/**
	 * ************************************************************************ Creates a new DVDatavault and attempts to import the object passed in. If the object cannot be imported, then null is
	 * returned as the DVDatavault cannot be assumed to be valid if the object doesn't import. If you need to create a DVDatavault and it doesn't matter of the import works, first create an empty
	 * DVDatavault then use DVDatavaultFactory.importTag() or DVDatavaultFactory.importDefault().
	 */
	// public static DVDatavault create( Object obj )
	// {
	// DVDatavault dv = new DVDatavault();
	// try{
	// if( !importDefault( dv, obj ) )
	// {
	// //warn them, but continue on
	// }
	// importDefault( dv, new GregorianCalendar() );
	// }
	// catch( ClassCastException cce )
	// {
	// cce.printStackTrace(System.out);
	// }
	// catch(Exception ex)
	// {
	// ex.printStackTrace(System.out);
	// }
	// return( dv );
	// }

	/**
	 * ************************************************************************ Creates a new empty DVDatavault.
	 */
	public static DVDatavault create() {
		DVDatavault dv = new DVDatavault();
		// importDefault( dv, new GregorianCalendar() );
		return (dv);
	}

	////////////////////////////////////////////////////////////////////////////
	// DVDatavault importers and mergers.
	////////////////////////////////////////////////////////////////////////////

	// /** ************************************************************************
	// * Imports using the supported import classes an object into a DVDatavault
	// * at the location specified by the DVDatavaultTag.
	// */
	// public static boolean importTag( String tag, DVDatavault dv, Object obj )
	// {
	// return( DVImport.doImport( new DVDatavaultTag(tag), dv, obj ) );
	// }
	//
	// /** ************************************************************************
	// * Imports using the supported import classes an object into a DVDatavault
	// * at the default location specified by the data-type import.
	// */
	// public static boolean importDefault( DVDatavault dv, Object obj )
	// {
	// return( DVImport.doImport( dv, obj ) );
	// }

	/**
	 * ************************************************************************ Merges 2 databags into one new one. If there is a conflict between the two DVDatavaults, the "primary" DVDatavault will
	 * over-write the values in the "secondary" DVDatavault.
	 */
	public static DVDatavault merge(DVDatavault primary, DVDatavault secondary) {
		DVDatavault newDv = new DVDatavault();
		Hashtable primaryHt = _extractSingleHashtable(primary.get(), new Hashtable(), DVDatavaultTag.TAG_ID + DVDatavaultTag.TAG_DELIM, 0);
		Hashtable secondaryHt = _extractSingleHashtable(secondary.get(), new Hashtable(), DVDatavaultTag.TAG_ID + DVDatavaultTag.TAG_DELIM, 0);

		for (Enumeration e = secondaryHt.keys(); e.hasMoreElements();) {
			Object key = e.nextElement();
			Object value = secondaryHt.get(key);
			newDv.set(key.toString(), value);
		}
		for (Enumeration e = primaryHt.keys(); e.hasMoreElements();) {
			Object key = e.nextElement();
			Object value = primaryHt.get(key);
			newDv.set(key.toString(), value);
		}
		return (newDv);
	}

	/**
	 * ************************************************************************ Returns a copy of the original DVDatavault.
	 */
	public static DVDatavault copy(DVDatavault dv) {
		DVDatavault newDv = new DVDatavault();
		Hashtable dvHt = _extractSingleHashtable(dv.get(), new Hashtable(), DVDatavaultTag.TAG_ID + DVDatavaultTag.TAG_DELIM, 0);

		for (Enumeration e = dvHt.keys(); e.hasMoreElements();) {
			Object key = e.nextElement();
			Object value = dvHt.get(key);
			newDv.set(key.toString(), value);
		}
		return (newDv);
	}

	////////////////////////////////////////////////////////////////////////////////
	// PRIVATE/PROTECTED METHODS
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////
	// DATA DUMPING METHODS
	////////////////////////////////////////////////////////////////////////////

	/**
	 * ************************************************************************ Return a dump of all data in the DVDatavault starting at the specified tag.
	 */
	protected static StringBuffer dump(DVDatavault self, String tag, String start, String end, String prefix, String suffix) {
		// setup
		StringBuffer out = new StringBuffer();
		out.append(start);

		// handle the exception at this level.
		try {
			Hashtable ht = (Hashtable) self.get(tag);
			out.append(DVDatavaultFactory._groupedDump(new StringBuffer(), ht, tag, prefix, suffix));
		} catch (Exception e) {
		}

		out.append(end);
		return (out);
	}

	/**
	 * ************************************************************************ Return a dump of all data in the DVDatavault.
	 */
	protected static StringBuffer dump(DVDatavault self, String start, String end, String prefix, String suffix) {
		StringBuffer out = new StringBuffer();
		out.append(start);
		out.append(DVDatavaultFactory._groupedDump(new StringBuffer(), self.get(), DVDatavaultTag.TAG_ID + DVDatavaultTag.TAG_DELIM, prefix, suffix));
		out.append(end);
		return (out);
	}

	/**
	 * A recursive method that dumps the data from a DataVault in a grouped fashion.
	 * 
	 * @param out
	 *            Buffer to dump output to.
	 * @param hash
	 *            hash to process
	 * @param tag
	 *            DataVault tag to start with.
	 * @param prefix
	 *            Prefix to add to output.
	 * @param suffix
	 *            Suffix to add to output.
	 * @return StringBuffer
	 */
	private static final StringBuffer _groupedDump(StringBuffer out, Hashtable hash, String tag, String prefix, String suffix) {
		// setup
		Hashtable vt = new Hashtable();
		String separater;

		// determine if this is a whole DataVault, or a fragement
		if (tag.endsWith(":")) {
			separater = "";
		} else {
			separater = DVDatavaultTag.LIST_DELIM;
		}

		// extract top level list.
		for (Enumeration e = hash.keys(); e.hasMoreElements();) {
			Object nextItem = e.nextElement();
			String nextTag = tag + separater + nextItem.toString();
			try {
				// if this is a hashtable, then we've got more to go!
				Hashtable section = (Hashtable) hash.get(nextItem);
				StringBuffer back = _groupedDump(out, section, nextTag, prefix, suffix);
				if (!e.hasMoreElements()) {
					return (back);
				}
			} catch (Exception ex) {
				// we're at the bottom, add it and then continue
				Object value = hash.get(nextItem);
				out.append(prefix + tag + DVDatavaultTag.LIST_DELIM + nextItem + " == " + value.toString() + suffix + "\n");
			}
		}
		return (out);
	}

	/**
	 * Recursively drill down into the DataVault and generate an XML document.
	 * 
	 * @param out
	 *            Buffer to dump output to.
	 * @param hash
	 *            hash to process
	 * @param tag
	 *            DataVault tag to start with.
	 * @param prefix
	 *            Prefix to add to output.
	 * @param suffix
	 *            Suffix to add to output.
	 * @return StringBuffer
	 */
	protected static final StringBuffer dumpXml(StringBuffer out, Hashtable hash, String tag, String prefix, String suffix) {
		return DVDatavaultFactory.dumpXml(out, hash, tag, prefix, suffix, UTDisplayFormatter.TAB, "\n");
	}

	/**
	 * Recursively drill down into the DataVault and generate an XML document.
	 * 
	 * @param out
	 *            Buffer to dump output to.
	 * @param hash
	 *            hash to process
	 * @param tag
	 *            DataVault tag to start with.
	 * @param prefix
	 *            Prefix to add to output.
	 * @param suffix
	 *            Suffix to add to output.
	 * @param tabelem
	 *            Tab element to add to output.
	 * @param endline
	 *            End of line element to add to output.
	 * @return StringBuffer
	 */
	protected static final StringBuffer dumpXml(StringBuffer out, Hashtable hash, String tag, String prefix, String suffix, String tabelem, String endline) {
		// setup
		Hashtable vt = new Hashtable();
		String separater;

		// determine if this is a whole DataVault, or a fragement
		if (tag.endsWith(":")) {
			separater = "";
		} else {
			separater = DVDatavaultTag.LIST_DELIM;
		}

		// extract top level list.
		for (Enumeration e = hash.keys(); e.hasMoreElements();) {
			Object nextItem = e.nextElement();
			String nextTag = tag + separater + nextItem.toString();

			try {
				// if this is a hashtable, then we've got more to go!
				Hashtable section = (Hashtable) hash.get(nextItem);
				out.append(prefix + "<" + nextItem + ">" + endline);
				StringBuffer back = dumpXml(out, section, nextTag, prefix + tabelem, suffix, tabelem, endline);

				out.append(prefix + "</" + nextItem + ">" + endline);
				if (!e.hasMoreElements()) {
					return (back);
				}
			} catch (Exception ex) {
				// we're at the bottom, add it and then continue
				Object value = hash.get(nextItem);
				out.append(prefix + "<" + nextItem + ">" + value.toString() + "</" + nextItem + ">" + endline);
			}
		}
		return (out);
	}

	////////////////////////////////////////////////////////////////////////////
	// This is the worker method that does the actual work of dumping the
	// DVDatavault data to a StringBuffer. This is an older method that has been
	// superceeded by _groupedDump(). I left it in incase its of some use to
	// someone in the future.
	//
	private static StringBuffer _dumpHash(Hashtable hash, String tag, String prefix, String suffix) {
		StringBuffer stb = new StringBuffer();
		Hashtable ht = _extractSingleHashtable(hash, new Hashtable(), tag, 0);

		for (Enumeration e = ht.keys(); e.hasMoreElements();) {
			Object key = e.nextElement();
			Object value = ht.get(key);
			stb.append(prefix + key.toString() + " == " + value + suffix + "\n");
		}
		return (stb);
	}

	////////////////////////////////////////////////////////////////////////////
	// extracts a single hashtable representation of the the DVDatavault. Level
	// should always be 0 (zero) going in AND, this is not as generic as it
	// may seem, it is specific to DVDatavault hash-of-hashes! (that's why its
	// private mister!)
	//
	protected static Hashtable _extractSingleHashtable(Hashtable ht, Hashtable out, String prefix, int level) {
		level++;
		Object name, value;
		String ct;
		String htTp = DVDatavaultFactory.CLASS_MAP_HASHTABLE;

		for (Enumeration e = ht.keys(); e.hasMoreElements();) {
			name = e.nextElement();
			value = ht.get(name);
			ct = value.getClass().getName();
			if (ct.equalsIgnoreCase(htTp)) {
				if (level < 2) {
					DVDatavaultFactory._extractSingleHashtable((Hashtable) value, out, prefix + name.toString(), level);
				} else {
					DVDatavaultFactory._extractSingleHashtable((Hashtable) value, out, prefix + "." + name.toString(), level);
				}
			} else {
				out.put(prefix + "." + name.toString(), value);
			}
		}
		return (out);
	}

	////////////////////////////////////////////////////////////////////////////
	// Pass in a 'hash of hashes' along with an ordered Vector of keys and a value
	// and the value will be added to the 'hash of hashes' according to the Vector.
	// A boolean is returned to indicated if the 'set' was successful. If 'true' is
	// returned then the value was set. If 'false' is returned then
	//
	protected static boolean _set(Hashtable ht, DVDatavaultTag tag, Object value) {
		// should we even proceed??
		if (tag == null) {
			return (false);
		}
		if (!tag.isValid()) {
			return (false);
		}

		// If the value is null, then change it to an empty string so we don't
		// exception. Its up the the calling application to really check to
		// see if we're setting a null value.
		if (value == null) {
			value = "";

			// setup
		}
		Hashtable curr = ht;
		Vector keys = (Vector) tag.getPath().clone(); // don't want to manipulate the original!
		String last = (String) keys.remove(keys.size() - 1); // extract the last element for the hashtable 'put' method.

		// walk the vector of elements, add new Hashtables where necessary
		for (Enumeration e = keys.elements(); e.hasMoreElements();) {
			String token = (String) e.nextElement();
			if (curr.containsKey(token)) { // hashtable has key, attempt to reference it
				try {
					curr = (Hashtable) curr.get(token);
				} catch (Exception ex) { // exception, process cannot continue, value setting
											// is not valid and must be terminated
					return (false);
				}
			} else { // hashtable doesn't contain key, create entry and attempt
						// to reference it.
				curr.put(token, new Hashtable());
				curr = (Hashtable) curr.get(token);
			}
		}
		// walked entire vector successfully, set the value and return true.
		curr.put(last, value);
		return (true);
	}

	////////////////////////////////////////////////////////////////////////////
	// Merges two hashtables into one. The hashtable merging into the DVDatavault
	// will override any colliding tags.
	//
	public static void _addHashtable(DVDatavault dv, Hashtable inBound, String tag)
	// protected static void _addHashtable( DVDatavault dv, Hashtable inBound, String tag )
	{
		for (Enumeration e = inBound.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			dv.set(tag + DVDatavaultTag.LIST_DELIM + key, inBound.get(key));
		}
	}

	////////////////////////////////////////////////////////////////////////////
	// Finds the Hashtable you are looking for in a "hash-of-hashes"
	//
	protected static Hashtable _findHashtable(Hashtable ht, Vector keys) {
		// setup
		Hashtable curr = ht;

		// walk Vector searching for Hashtable.
		for (Enumeration e = keys.elements(); e.hasMoreElements();) { // attempt to reference the next key's hashtable
			String token = (String) e.nextElement();
			try { // take the current hashtable and attempt to access
					// the next level down.
				curr = (Hashtable) curr.get(token);
				if (curr == null) {
					return null;
				}
			} catch (Exception ex) { // exception failure results in a null return value
				return (null);
			}
		}
		// found! return value by calling hashtable.get()
		return (curr);
	}

}
