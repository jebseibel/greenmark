package com.greenmark.utils.dv;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DVDatavaultTag</p>
 * <p>Description: This class is a wrapper class for DataVault resolve strings.  This class
 * validates and preps the strings for use in DataVault.  Generally speaking
 * this class is not designed for direct use.  It is part of the overall
 * DataVault package.  But, if you must, you can use it.<P>
 *
 * DVDatavaultTag is broken out as a separate object to make it easier to
 * manipulate the object that handles the DataVault "pointer" strings.  Doing
 * this will make it easy to add support for additional tagging structures.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DVDatavaultTag {
    public static final String CLASSNAME = "DVDatavaultTag";

    // control constants
    public static final String TAG_ID = "DV";
    public static final char TAG_DELIM_CHAR = ':';
    public static final char LIST_DELIM_CHAR = '.';
    public static final String TAG_DELIM = ":";
    public static final String LIST_DELIM = ".";
    private static final String CLASS_MAP_THIS = "com.disney.dcplic.common.DVDatavaultTag";

    // class variables
    private Vector thePath;
    private String tag;
    private boolean isValid;

    /////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////

    //////////////////////////////

    /**
     * Creates a new DVDatavaultTag object.
     *
     * @param <CODE>tag</CODE> String The tag to create object with.
     */
    public DVDatavaultTag(String tag) {
        DVDatavaultTag._constructor(this, tag);
    }

    //////////////////////////////

    /**
     * Creates a new DVDatavaultTag object.
     *
     * @param <CODE>tag</CODE> String The tag to create object with.
     */
    public DVDatavaultTag(StringBuffer tag) {
        DVDatavaultTag._constructor(this, tag.toString());
    }

    /////////////////////////////////////////////////
    // INSTANCE METHODS
    /////////////////////////////////////////////////

    //////////////////////////////

    /**
     * Verified the validity of the tag. This is a convience method so you don't have to type an object if you don't want to.
     *
     * @param <CODE>tag</CODE> Object The object to test for validity.
     * @return <CODE>boolean</CODE>.
     */
    public static boolean isValid(Object tag) {
        return (DVDatavaultTag._isValid(tag));
    }

    //////////////////////////////

    //////////////////////////////
    // rebuild string representation of this flag
    //
    private static StringBuffer _rebuildStringTag(DVDatavaultTag dvt) {
        StringBuffer sb = new StringBuffer();
        sb.append(DVDatavaultTag.TAG_ID);
        sb.append(DVDatavaultTag.TAG_DELIM);
        for (Enumeration e = dvt.thePath.elements(); e.hasMoreElements(); ) {
            String item = e.nextElement().toString();
            if (e.hasMoreElements()) {
                sb.append(item);
                sb.append(DVDatavaultTag.LIST_DELIM);
            } else {
                sb.append(item);
            }
        }
        return (sb);
    }

    //////////////////////////////

    //////////////////////////////
    // common constructor code is placed here
    //
    private static void _constructor(DVDatavaultTag self, String tag) {
        self.tag = tag;

        // validate tag and return list
        StringBuffer list = DVDatavaultTag._parseTagId(tag);
        if (list == null) {
            self.isValid = false;
            return;
        }

        // parse vector of hash-of-hashes items
        self.thePath = DVDatavaultTag._parsePath(list);

        // are we valid?
        self.isValid = self.thePath != null;
    }

    //////////////////////////////

    //////////////////////////////
    // Verified the validity of a String as a possible databag tag.
    //
    private static boolean _isValid(Object tag) {
        if (tag != null) {
            // use java.Object.getClass() to match the "string" name of the class
            // passed in and compare it to the hard-coded classmap
            String test = tag.getClass().getName();
            if (test.equalsIgnoreCase(DVDatavaultTag.CLASS_MAP_THIS)) {
                // if this is already a DVDatavaultTag, then return its validity
                DVDatavaultTag newTag = (DVDatavaultTag) tag;
                return (newTag.isValid());
            } else if (test.equalsIgnoreCase(DVDatavault.CLASS_MAP_STRING)) {
                // parse out tag and list
                StringBuffer list = DVDatavaultTag._parseTagId(tag.toString());
                return list != null;
                // well, its valid!
            } else {
                return (false);
            }
        } else {
            // null values cannot be databag tags!!
            return (false);
        }
    }

    //////////////////////////////

    //////////////////////////////
    // extract a vector that represents the hash-of-hashes list.
    //
    private static Vector _parsePath(StringBuffer list) {
        Vector rt = new Vector();
        StringTokenizer st = new StringTokenizer(list.toString(), DVDatavaultTag.LIST_DELIM);
        if (st.countTokens() > 0) {
            String nt;
            while (st.hasMoreTokens()) {
                nt = st.nextToken();
                rt.addElement(nt);
            }
        } else {
            return (null);
        }
        return (rt);
    }

    //////////////////////////////

    //////////////////////////////
    // receives the full tag string and parses out list validating that the string starts
    // with 'DV:' as a verifier for the fact that it is a Data Vault Tag.
    //
    private static StringBuffer _parseTagId(String tag) {
        // if a tag is a null string then its not a tag!!
        if (tag.equalsIgnoreCase("")) {
            return (null);
        }
        // extract list from valid tag
        StringTokenizer st = new StringTokenizer(tag, DVDatavaultTag.TAG_DELIM);
        if (st.nextToken().equalsIgnoreCase(DVDatavaultTag.TAG_ID)) {
            return (new StringBuffer(st.nextToken()));
        } else {
            return (null);
        }
    }

    //////////////////////////////

    /**
     * Adds a segment to an existing DVDatavaultTag.
     */
    public void push(String pushValue) {
        this.thePath.add(pushValue);
        this.tag = DVDatavaultTag._rebuildStringTag(this).toString();
    }

    //////////////////////////////

    /**
     * Removes the last segment of the tag.
     */
    public String pop() {
        int index = this.thePath.size();
        String out = this.thePath.remove(--index).toString();
        this.tag = DVDatavaultTag._rebuildStringTag(this).toString();
        return (out);
    }

    //////////////////////////////

    /**
     * Get the DataVault hash-of-hashes path vector. This is the main method that is used by DataVault to aquire the path to be resolved.
     *
     * @return <CODE>Vector</CODE> Or null if the tag is unvalid.
     */
    public Vector getPath() {
        if (this.isValid) {
            return (this.thePath);
        }
        return (null);
    }

    /////////////////////////////////////////////////
    // PRIVATE METHODS
    /////////////////////////////////////////////////

    /**
     * Compare a string to this DVDatavaultTag.
     */
    public boolean equals(String compare) {
        return (this.tag.equals(compare));
    }

    /**
     * Compare a string to this DVDatavaultTag.
     */
    public boolean equals(DVDatavaultTag compare) {
        String compareIt = compare.toString();
        return (this.tag.equals(compareIt));
    }

    /**
     * Get the original tag that was used to create this instance.
     *
     * @return <CODE>String</CODE>.
     */
    public String getTag() {
        if (this.isValid) {
            return (this.tag);
        }
        return (null);
    }

    /**
     * Return the String value of this object. Returns the same result at 'getTag()'.
     *
     * @return <CODE>String</CODE>.
     */
    public String toString() {
        return (this.getTag());
    }

    /**
     * Verified the validity of the tag. This method is differs from the class method in that it only works on an instance. Example: [instance].isValid().
     *
     * @return <CODE>boolean</CODE>.
     */
    public boolean isValid() {
        return (this.isValid);
    }

}
