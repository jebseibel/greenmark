package com.greenmark.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTXmlUtils</p>
 * <p>Description: The <code><B>UTXmlUtils</B></CODE> is used to parse out a section from an XML string.
 * This is a very basic parser and cannot handle XML trees or distinguish between identical
 * tags at different levels.  When it processed, it returns the first instance of the XML tag
 * that it finds.  If you need a full featured XML parser use the W3C SAX DOM parser.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class UTXmlUtils {
    public static final String CLASSNAME = "UTXmlUtils";

    ///////////////////////////////////
    // PUBLIC METHODS
    ///////////////////////////////////

    /**
     * This method returns all information that resides between matching XML tags (inside the <code>xml</code> parameter) as specified by the <code>xmlTag</CODE> parameter.
     * <p>
     *
     * @param <CODE>xmlTag</CODE>  - The xml tag to extract.
     * @param <CODE>xmldata</CODE> - The xml code to extract it from.
     */
    public static String getXmlData(String xmldata, String xmlTag) {
        return UTXmlUtils.getXmlData(xmldata, xmlTag, 0);
    }

    /**
     * This method returns all information that resides between matching XML tags (inside the <code>xml</code> parameter) as specified by the <code>xmlTag</CODE> parameter. It then attempts to return
     * the extraced data as a float. On error (Exception) 0 is returned.
     * <p>
     *
     * @param <CODE>xmlTag</CODE>  - The xml tag to extract.
     * @param <CODE>xmldata</CODE> - The xml code to extract it from.
     */
    public static float getXmlDataAsFloat(String xmldata, String xmlTag) {
        String stringvalue = UTXmlUtils.getXmlData(xmldata, xmlTag, 0);

        // early exit, if empty return default value
        if (UTUtils.isNorE(stringvalue))
            return 0;

        // attempt to return a float. On Exception return default value
        try {
            return Float.parseFloat(stringvalue);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean getXmlDataAsBoolean(String xmldata, String xmlTag) {
        String stringvalue = UTXmlUtils.getXmlData(xmldata, xmlTag, 0);

        // early exit, if empty return default value
        if (UTUtils.isNorE(stringvalue))
            return false;

        if (stringvalue.equalsIgnoreCase("YES"))
            return true;

        if (stringvalue.equalsIgnoreCase("NO"))
            return false;

        if (stringvalue.equalsIgnoreCase("1"))
            return true;

        if (stringvalue.equalsIgnoreCase("0"))
            return false;

        if (stringvalue.equalsIgnoreCase("TRUE"))
            return true;

        if (stringvalue.equalsIgnoreCase("FALSE"))
            return false;

        return false;// Default is false
    }

    /**
     * This method returns all information that resides between matching XML tags (inside the <code>xml</code> parameter) as specified by the <code>xmlTag</CODE> parameter. It then attempts to return
     * the extraced data as a double. On error (Exception) 0 is returned.
     * <p>
     *
     * @param <CODE>xmlTag</CODE>  - The xml tag to extract.
     * @param <CODE>xmldata</CODE> - The xml code to extract it from.
     */
    public static double getXmlDataAsDouble(String xmldata, String xmlTag) {
        String stringvalue = UTXmlUtils.getXmlData(xmldata, xmlTag, 0);

        // early exit, if empty return default value
        if (UTUtils.isNorE(stringvalue))
            return 0;

        // attempt to return a float. On Exception return default value
        try {
            return Double.parseDouble(stringvalue);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * This method returns all information that resides between matching XML tags (inside the <code>xml</code> parameter) as specified by the <code>xmlTag</CODE> parameter. It then attempts to return
     * the extraced data as a long. On error (Exception) 0 is returned.
     * <p>
     *
     * @param <CODE>xmlTag</CODE>  - The xml tag to extract.
     * @param <CODE>xmldata</CODE> - The xml code to extract it from.
     */
    public static long getXmlDataAsLong(String xmldata, String xmlTag) {
        String stringvalue = UTXmlUtils.getXmlData(xmldata, xmlTag, 0);

        // early exit, if empty return default value
        if (UTUtils.isNorE(stringvalue))
            return 0;

        // attempt to return a long. On Exception return default value
        try {
            return Long.parseLong(stringvalue);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * This method returns all information that resides between matching XML tags (inside the <code>xml</code> parameter) as specified by the <code>xmlTag</CODE> parameter. It then attempts to return
     * the extraced data as an int. On error (Exception) 0 is returned.
     * <p>
     *
     * @param <CODE>xmlTag</CODE>  - The xml tag to extract.
     * @param <CODE>xmldata</CODE> - The xml code to extract it from.
     */
    public static int getXmlDataAsInt(String xmldata, String xmlTag) {
        String stringvalue = UTXmlUtils.getXmlData(xmldata, xmlTag, 0);

        // early exit, if empty return default value
        if (UTUtils.isNorE(stringvalue))
            return 0;

        // attempt to return a float. On Exception return default value
        try {
            return Integer.parseInt(stringvalue);
        } catch (Exception e) {
            return 0;
        }
    }

    // This expects the input to be in the format: UTDatetime.DATE_FORMAT_DBDATE_STRING = "yyyy-MM-dd kk:mm:ss"
    // Returns null on error (bad input datetime format)
    public static LocalDateTime getXmlDataAsLDT(String xmldata, String xmlTag) {
        String stringvalue = UTXmlUtils.getXmlData(xmldata, xmlTag, 0);

        // early exit, if empty return default value
        if (UTUtils.isNorE(stringvalue))
            return null;

        try {
            LocalDateTime returnVal = UTDatetime.fromDbString(stringvalue);
            return returnVal;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns all information that resides between matching XML tags (inside the <code>xml</code> parameter) as specified by the <code>xmlTag</CODE> parameter.
     * <p>
     *
     * @param <CODE>xmlTag</CODE>         - The xml tag to extract.
     * @param <CODE>xmldata</CODE>        - The xml code to extract it from.
     * @param <CODEintStartPosition</CODE - starting location
     */
    public static String getXmlData(String xmldata, String xmlTag, int intStartPosition) {
        // Define the vars
        String strTagFront;
        String strTagBack;
        String strReturn = "";
        String xml = xmldata.substring(intStartPosition);

        // Drop both the XML (from the class var) and search string to lowercase.
        String StrClassXml = xml;
        String StrLcClassXml = StrClassXml.toLowerCase();
        String StrLcStrPattern = xmlTag.toLowerCase();

        int pos = 0;

        // initialize the values
        int intStartTag = -1;
        int intAltStartTag = -1;
        int intStart = -1;

        // Look for the location of the front tag.
        intStartTag = StrLcClassXml.indexOf("<" + StrLcStrPattern + ">");

        if (intStartTag != -1) {
            // we found one! Find the back
            intStart = intStartTag + StrLcStrPattern.length() + 2;
        } else {
            // if that didnt find it, look to see if it has attributes
            // we search for a space after it to make sure that there isnt another
            // element that starts with the same name.
            intAltStartTag = StrLcClassXml.indexOf("<" + StrLcStrPattern + " ");
            if (intAltStartTag != -1) {
                // we did find a tag with attributes. Now find the back and add one for the >
                intStart = StrLcClassXml.indexOf(">", intAltStartTag);
                intStart++;
            }
        }

        // If found, get the return string
        if (intStart != -1) {
            // Now find the back tag.
            strTagBack = "</" + StrLcStrPattern + ">";
            int intTagBack = StrLcClassXml.indexOf(strTagBack);

            if (intTagBack > intStartTag) {
                // Use these two numbers to get the text between the two strings
                strReturn = StrClassXml.substring(intStart, intTagBack);
                strReturn.trim();
            } else {
                // return null if we can't find a corresponding closing tag.
                strReturn = "";
            }
        } else {
            // return null if we can't find the desired tag.
            strReturn = "";
        }

        // Return the result
        return (strReturn);
    }

    /**
     * An example is worth a thousand words:
     * <p>
     * StringBuffer x = new StringBuffer(); x.append("<a>"); x.append("<h><id>1</id> <num>AA</num> <data>This is it 111</data> </h>"); x.append("<h><id>2</id> <num>BB</num> <data>This is it 222</data>
     * </h>"); x.append("<h><id>3</id> <num>CC</num> <data>This is it 333</data> </h>"); x.append("<h><id>4</id> <num>DD</num> <data>This is it 444</data> </h>"); x.append("</a>"); Hashtable a =
     * UTXmlUtils.getNodesToHashtable( x.toString(), "h", "id" );
     * <p>
     * Result: 1=<id>1</id><num>AA</num><data>This is it 111</data> 2=<id>2</id><num>BB</num><data>This is it 222</data> 3=<id>3</id><num>CC</num><data>This is it 333</data>
     * 4=<id>4</id><num>DD</num><data>This is it 444</data>
     *
     * @param xmldata - the xml data to be acted upon.
     * @param xmltag  - the xml tag nodes to pull
     * @param xmlname - the name to use as a node
     * @return
     */
    public static Hashtable getNodesToHashtable(String xmldata, String xmltag, String xmlname) {
        Hashtable hReturn = new Hashtable();
        int intEnd;
        String childInfo;
        String nameInfo;

        int intBeg = xmldata.indexOf("<" + xmltag + ">");

        try {
            while (intBeg != -1) {
                // find the back of the tag
                intEnd = xmldata.indexOf("</" + xmltag + ">", intBeg);

                childInfo = xmldata.substring(intBeg + xmltag.length() + 2, intEnd);
                nameInfo = UTXmlUtils.getXmlData(childInfo, xmlname);
                hReturn.put(nameInfo, childInfo);

                // look for the next one
                intBeg = xmldata.indexOf("<" + xmltag + ">", intBeg + 1);
            }
            return hReturn;
        } catch (Exception e) {
            // System.out.println("Exception caught in XMLParserTools.getNodesToHashtable() ");
            return hReturn;
        }
    }

    public static Vector getNodesToVector(String xml) {
        Vector vReturn = new Vector();
        try {
            int intTagBackClose;
            for (int marker = 0; marker != -1; marker = xml.indexOf("<", intTagBackClose + 1)) {
                int intTagBeg = xml.indexOf("<", marker) + 1;
                int intTagEndSpace = xml.indexOf(" ", intTagBeg + 1);
                int intTagEndClose = xml.indexOf(">", intTagBeg + 1);
                int intTagEnd = _getTagEnd(intTagEndSpace, intTagEndClose);
                String tagg = xml.substring(intTagBeg, intTagEnd);
                int intTagBackStart = xml.indexOf("</".concat(tagg), intTagEnd);
                intTagBackClose = xml.indexOf(">", intTagBackStart + 1);
                String childInfo = xml.substring(intTagBeg - 1, intTagBackClose + 1);
                vReturn.addElement(childInfo);
            }

            Vector vector = vReturn;
            return vector;
        } catch (Exception e) {
            System.out.println("Exception caught in XMLParserTools.GetNodes(), message: " + e.getMessage());
        }
        Vector vector1 = vReturn;
        return vector1;
    }

    /**
     *
     */
    public static SortedMap getNodesToSortedMap(String xmldata, String xmltag, String xmlname) {
        SortedMap hReturn = new TreeMap();
        int intEnd;
        String childInfo;
        String nameInfo;

        int intBeg = xmldata.indexOf("<" + xmltag + ">");

        try {
            while (intBeg != -1) {
                // find the back of the tag
                intEnd = xmldata.indexOf("</" + xmltag + ">", intBeg);

                childInfo = xmldata.substring(intBeg + xmltag.length() + 2, intEnd);
                nameInfo = UTXmlUtils.getXmlData(childInfo, xmlname);
                hReturn.put(nameInfo, childInfo);

                // look for the next one
                intBeg = xmldata.indexOf("<" + xmltag + ">", intBeg + 1);
            }
            return hReturn;
        } catch (Exception e) {
            // System.out.println("Exception caught in XMLParserTools.getNodesToHashtable() ");
            return hReturn;
        }
    }

    public static final Vector getElementsToVector(String xmldata, String xmlTag) {
        Vector vReturn = new Vector(30);

        // Use a try/catch for bad incoming data
        try {
            int marker = 0;
            while (marker != -1) {
                // get in and out points
                int intName1TagBeg = xmldata.indexOf("<" + xmlTag + ">", marker);
                int intName1TagEnd = intName1TagBeg + 2 + xmlTag.length();
                int intName2TagBeg = xmldata.indexOf("</" + xmlTag + ">", intName1TagEnd);

                // extract the actual information
                String childInfo = xmldata.substring(intName1TagEnd, intName2TagBeg);

                // add to output hashtable
                if (childInfo != null) {
                    vReturn.add(childInfo);
                }

                // look for the next one
                marker = xmldata.indexOf("<" + xmlTag + ">", intName2TagBeg + 1);
            }
            return vReturn;
        } catch (Exception e) {
            return vReturn;
        }
    }

    //////////////////////////////

    /**
     * This method returns the actual tag from the xml. <br>
     * Note: this returns only the first tag it finds.<br>
     * Also, it does not return any subsequent closing tags.
     * <p>
     * <CODE>Example</CODE>:
     * <p>
     * <code>Sent in:</code> <br>
     * &lt;xml&gt; <br>
     * &lt;first attr1="aaa" attr2="bbb"&gt; <br>
     * &lt;name&gt;test&lt;/name&gt; <br>
     * &lt;/first&gt; <br>
     * &lt;/xml&gt; <br>
     * <br>
     * <code>Returned:</code> <br>
     * &lt;first attr1="aaa" attr2="bbb"&gt;
     * <p>
     *
     * @param <CODE>xmldata</CODE> - The xml to act upon.
     * @param <CODE>xmlTag</CODE>  - The xml tag to return.
     */
    public static String getXmlTag(String xmldata, String xmlTag) {
        return UTXmlUtils.getXmlTag(xmldata, xmlTag, 0);
    }

    /**
     * This method returns the actual tag from the xml. <br>
     * Note: this returns only the first tag it finds.<br>
     * Also, it does not return any subsequent closing tags.
     * <p>
     * <CODE>Example</CODE>:
     * <p>
     * <code>Sent in:</code> <br>
     * &lt;xml&gt; <br>
     * &lt;first attr1="aaa" attr2="bbb"&gt; <br>
     * &lt;name&gt;test&lt;/name&gt; <br>
     * &lt;/first&gt; <br>
     * &lt;/xml&gt; <br>
     * <br>
     * <code>Returned:</code> <br>
     * &lt;first attr1="aaa" attr2="bbb"&gt;
     * <p>
     *
     * @param <CODE>xmldata</CODE>        - The xml to act upon.
     * @param <CODE>xmlTag</CODE>         - The xml tag to return.
     * @param <CODEintStartPosition</CODE - starting location
     */
    public static String getXmlTag(String xmldata, String xmlTag, int intStartPosition) {
        // Define the vars
        String strTagFront;
        String strTagBack;
        String strReturn;
        String xml = xmldata.substring(intStartPosition);

        // Drop both the XML (from the class var) and search string to lowercase.
        String StrClassXml = xml;
        String StrLcClassXml = StrClassXml.toLowerCase();
        String StrLcStrPattern = xmlTag.toLowerCase();

        // create the tag to search for
        strTagFront = "<" + StrLcStrPattern;

        // Look for the location of the front tag.
        int intStartTag = StrLcClassXml.indexOf(strTagFront);

        // If found, get the return string
        if (intStartTag != -1) {
            // Add the length of the of the front tag.
            // We do this because we want to return what is inside the tag, not
            // what is inside AND the tag.
            int intLenTagFront = strTagFront.length();
            int intStart = intStartTag + intLenTagFront;

            // Now find the back tag.
            strTagBack = ">";
            int intTagBack = StrLcClassXml.indexOf(strTagBack, intStartTag);

            if (intTagBack > intStartTag) {
                // Use these two numbers to get the text between the two strings
                strReturn = StrClassXml.substring(intStart - intLenTagFront, intTagBack + 1);
                strReturn.trim();
            } else {
                // return null if we can't find a corresponding closing tag.
                strReturn = "";
            }
        } else {
            // return null if we can't find the desired tag.
            strReturn = "";
        }

        // Return the result
        return (strReturn);
    }

    ///////////////
    ///////////////
    private static int _getTagEnd(int A, int B) throws Exception {
        if (A == -1 && B == -1) {
            throw new Exception("bad parsing");
        }
        int Z;
        if (A != -1 && B == -1) {
            Z = A;
        } else if (B != -1 && A == -1) {
            Z = B;
        } else if (A < B) {
            Z = A;
        } else if (B < A) {
            Z = B;
        } else {
            throw new Exception("bad parsing");
        }
        return Z;
    }

    /**
     * This method returns all XML elements inside an xml table as a hashtable.
     * <p>
     * They are returned in a hashtable in the format:<br>
     * key = tag name<br>
     * value = value within tag<br>
     * <p>
     * This method ASSUMES:<br>
     * 1. That the xml is fully formated<br>
     * 2. Element tags of the form <child/> are not supported<br>
     * <p>
     * Parsing example:<br>
     * &lt;inner1&gt;info&lt;/inner1&gt;<br>
     * &lt;inner2&gt;info2&lt;/inner2&gt;<br>
     * <p>
     * Returns hashtable:<br>
     * <b>key:</b> inner1 <b>value:</b> info1<br>
     * <b>key:</b> inner2 <b>value:</b> info2<br>
     * <p>
     * Use this after the method 'parseOutXmlTag'!
     * <p>
     *
     * @param <CODE>xmldata</CODE> - The xml code to extract elements from.
     */
    public static final Hashtable getElementsToHashtable(String xmldata) {
        // defs
        Hashtable hshReturn = new Hashtable();

        // Use a try/catch for bad incoming data
        try {
            int marker = 0;
            while (marker != -1) {
                // get in and out points
                int intName1TagBeg = xmldata.indexOf("<", marker);
                int intName1TagEnd = xmldata.indexOf(">", intName1TagBeg + 1);
                int intName2TagBeg = xmldata.indexOf("</", intName1TagEnd);

                // extract the actual information
                String childName = xmldata.substring(intName1TagBeg + 1, intName1TagEnd);
                String childInfo = xmldata.substring(intName1TagEnd + 1, intName2TagBeg);

                // add to output hashtable
                if (childInfo != null) {
                    hshReturn.put(childName, childInfo);
                }

                // look for the next one
                marker = xmldata.indexOf("<", intName2TagBeg + 1);
            }
            return hshReturn;
        } catch (Exception e) {
            return hshReturn;
        }
    }

    /**
     * This method returns all XML elements inside an xml table as a SortedMap.
     * <p>
     * They are returned in a sortedmap in the format:<br>
     * key = tag name<br>
     * value = value within tag<br>
     * <p>
     * This method ASSUMES:<br>
     * 1. That the xml is fully formated<br>
     * 2. Element tags of the form <child/> are not supported<br>
     * <p>
     * Parsing example:<br>
     * &lt;inner1&gt;info&lt;/inner1&gt;<br>
     * &lt;inner2&gt;info2&lt;/inner2&gt;<br>
     * <p>
     * Returns sortedMap:<br>
     * <b>key:</b> inner1 <b>value:</b> info1<br>
     * <b>key:</b> inner2 <b>value:</b> info2<br>
     * <p>
     * Use this after the method 'parseOutXmlTag'!
     * <p>
     *
     * @param <CODE>xmldata</CODE> - The xml code to extract elements from.
     */
    public static final SortedMap getElementsToSortedMap(String xmldata) {
        // defs
        SortedMap hshReturn = new TreeMap();

        // Use a try/catch for bad incoming data
        try {
            int marker = 0;
            while (marker != -1) {
                // get in and out points
                int intName1TagBeg = xmldata.indexOf("<", marker);
                int intName1TagEnd = xmldata.indexOf(">", intName1TagBeg + 1);
                int intName2TagBeg = xmldata.indexOf("</", intName1TagEnd);

                // extract the actual information
                String childName = xmldata.substring(intName1TagBeg + 1, intName1TagEnd);
                String childInfo = xmldata.substring(intName1TagEnd + 1, intName2TagBeg);

                // add to output hashtable
                if (childInfo != null) {
                    hshReturn.put(childName, childInfo);
                }

                // look for the next one
                marker = xmldata.indexOf("<", intName2TagBeg + 1);
            }
            return hshReturn;
        } catch (Exception e) {
            return hshReturn;
        }
    }

    /**
     *
     */
    private static void test() {
        try {
            StringBuffer stb = new StringBuffer();
            stb.append("<PROPERTIES>");
            stb.append("<PROPERTY>");
            stb.append("<SOURCE>src1</SOURCE>");
            stb.append("<TARGET>tar1</TARGET>");
            stb.append("</PROPERTY>");
            stb.append("<PROPERTY>");
            stb.append("<SOURCE>src2</SOURCE>");
            stb.append("<TARGET>tar2</TARGET>");
            stb.append("</PROPERTY>");
            stb.append("</PROPERTIES>");

            //////////////////////////////////////////////////////////
            // getXmlData
            System.out.println();
            System.out.println("getXmlData==========================================");
            String getXmlData = UTXmlUtils.getXmlData(stb.toString(), "PROPERTY");
            System.out.println("getXmlData [" + getXmlData + "]");

            //////////////////////////////////////////////////////////
            // getXmlTag
            System.out.println();
            System.out.println("getXmlTag==========================================");
            String getXmlTag = UTXmlUtils.getXmlTag(stb.toString(), "PROPERTY");
            System.out.println("getXmlTag  [" + getXmlTag + "]");

            //////////////////////////////////////////////////////////
            // getElementsToVector
            System.out.println();
            System.out.println("getElementsToVector==========================================");
            stb = new StringBuffer();
            stb.append("<PROPERTY>");
            stb.append("<SOURCE>src1</SOURCE>");
            stb.append("<TARGET>tar1</TARGET>");
            stb.append("</PROPERTY>");
            stb.append("<PROPERTY>");
            stb.append("<SOURCE>src2</SOURCE>");
            stb.append("<TARGET>tar2</TARGET>");
            stb.append("</PROPERTY>");

            Vector vv = UTXmlUtils.getElementsToVector(stb.toString(), "PROPERTY");
            for (Enumeration ev = vv.elements(); ev.hasMoreElements(); ) {
                String name = (String) ev.nextElement();
                System.out.println("getElementsToVector  [" + name + "]");
            }

            //////////////////////////////////////////////////////////
            // getElementsToHashtable
            System.out.println();
            System.out.println("getElementsToHashtable==========================================");
            stb = new StringBuffer();
            stb.append("<SOURCE>src1</SOURCE>");
            stb.append("<TARGET>tar1</TARGET>");

            Hashtable hh = UTXmlUtils.getElementsToHashtable(stb.toString());
            for (Enumeration eh = hh.keys(); eh.hasMoreElements(); ) {
                String name = (String) eh.nextElement();
                String valu = (String) hh.get(name);
                System.out.println("name [" + name + "]  valu [" + valu + "]");
            }

            //////////////////////////////////////////////////////////
            // getNodesToHashtable
            System.out.println();
            System.out.println("getNodesToHashtable==========================================");
            stb = new StringBuffer();
            stb.append("<PROPERTY>");
            stb.append("<SOURCE>src1</SOURCE>");
            stb.append("<TARGET>tar1</TARGET>");
            stb.append("</PROPERTY>");
            stb.append("<PROPERTY>");
            stb.append("<SOURCE>src2</SOURCE>");
            stb.append("<TARGET>tar2</TARGET>");
            stb.append("</PROPERTY>");

            Hashtable hn = UTXmlUtils.getNodesToHashtable(stb.toString(), "PROPERTY", "SOURCE");
            for (Enumeration en = hn.keys(); en.hasMoreElements(); ) {
                String name = (String) en.nextElement();
                String valu = (String) hn.get(name);
                System.out.println("name [" + name + "]  valu [" + valu + "]");
            }

        } catch (Exception ex) {
            System.out.println("Exception.getMessage() [" + ex.getMessage() + "]");
        }

    }

    /**
     * This method returns all information that resides between matching XML tags (inside the <code>xml</code> parameter) as specified by the <code>xmlPath</CODE> parameter.
     * <p>
     *
     * @param <CODE>xmlTag</CODE>  - The xml tag to extract.
     * @param <CODE>xmlPath</CODE> - The xpath to the data element
     */
    public static String getDataFromXpath(String xmldata, String xmlPath) {
        String result = "";
        try {
            ArrayList paths = new ArrayList();
            StringTokenizer st = new StringTokenizer(xmlPath, ".");
            while (st.hasMoreTokens()) {
                String path = st.nextToken();
                // System.out.println("path ["+ path +"]" );
                paths.add(path);
            }
            // System.out.println("paths ["+ paths +"]" );
            // System.out.println("======================================" );

            String data = xmldata;
            for (int i = 0; i < paths.size(); i++) {
                String path = (String) paths.get(i);
                result = UTXmlUtils.getXmlData(data, path);
                //  log.debug( "data [" + data + "]" );
            }
            //  log.debug( "final data [" + data + "]" );

        } catch (Exception ex) {
            log.error("Caught General Exception " + CLASSNAME + ".getDataFromXpath:[" + ex.getMessage() + "]");
        }

        return result;
    }

}
