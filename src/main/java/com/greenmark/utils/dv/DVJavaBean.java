package com.greenmark.utils.dv;

import com.greenmark.utils.UTUtils;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DVJavaBean</p>
 * <p>Description: This class is a thin wrapper around the datavault object.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DVJavaBean {
    public static final String CLASSNAME = "DVJavaBean";

    public String id = "";
    public DVDatavault dv = new DVDatavault();

    /////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////
    public DVJavaBean() {
    }

    /**
     * Instantiates an object with the passed in xml.
     */
    public DVJavaBean(String xml) {
        this.populateWithXml(xml);
    }

    /////////////////////////////////////////////////
    // PUBLIC METHODS
    /////////////////////////////////////////////////

    /**
     * Merges the second objects's values into the first one.
     * <p>
     * Where the values differ, the primary objects's value is used.
     *
     * @param primary   - primary object
     * @param secondary - secondary object
     */
    public static final DVJavaBean merge(DVJavaBean primary, DVJavaBean secondary) {
        // merge the dv
        if (UTUtils.isNotNorE(secondary.dv.toXml().toString())) {
            DVDatavault mergedDv = DVDatavaultFactory.merge(primary.dv, secondary.dv);
            primary.dv = mergedDv;
        }
        return primary;
    }

    /**
     * Use this method to set data into the JavaBean.
     * <p>
     * Please note that all xpath's must begin with the string "DV:"!
     *
     * @param xpath - path to the data
     * @param data  - data to store at this path
     */
    public final void setData(String xpath, String data) {
        dv.set(xpath, data);
    }

    /**
     * This method is used to retrieve data out of the object.
     * <p>
     * Please note that all xpath's must begin with the string "DV:"!
     *
     * @param xpath
     * @return
     */
    public final String getData(String xpath) {
        return dv.getString(xpath);
    }

    /************************************************************************************************************/
    /************************************************************************************************************/

    /**
     * This method returns all the data out of this object as XML.
     *
     * @return
     */
    public final String getData() {
        return dv.toXml().toString();
    }

    /**
     * Use this method to send in an xml file and have it be parsed into the object. Please note that all xml passed in will be placed at the starting node "DV:XML".
     *
     * @param xml   - the xml that will be placed into the object
     * @param trace -
     */

    public void populateWithXml(String xml) {
        // extract the information
        this.dv = DVImportXml.extract(xml);
    }

    /**
     * This method accepts a hashtable of parameters and places them into the object at the level "DV:". Please note that this function appends "DV:" to your key values before placing them in the
     * object.
     *
     * @param params - Hashtable of the params
     * @param trace
     */
    public void populateWithParams(Hashtable params) {
        for (Enumeration e = params.keys(); e.hasMoreElements(); ) {
            String xpath = (String) e.nextElement();
            String vdata = (String) params.get(xpath);
            this.dv.set("DV:" + xpath, vdata);
        }
    }

    /**
     * This method accepts a hashtable of parameters and places them into the object at the level specified by the passed in xpath.
     * <p>
     * Please note that all xpath's must begin with the string "DV:"! If they do not start with that label the populate will fail
     *
     * @param params - Hashtable of the params
     * @param trace
     */
    public void populateWithParams(Hashtable params, String xpath) {
        for (Enumeration e = params.keys(); e.hasMoreElements(); ) {
            String thisxpath = (String) e.nextElement();
            if (thisxpath.startsWith("DV:")) {
                String vdata = (String) params.get(thisxpath);
                this.dv.set(thisxpath, vdata);
            }
        }
    }

    /**
     * Returns the contents of the object as a string
     *
     * @return
     */
    public final String toString() {
        return this.dv.dump().toString();
    }

    /**
     * Returns the contents of the object as an xml string.
     *
     * @param endline - end of line delimiter
     * @return
     */
    public final String toXml() {
        return this.dv.toXml("", "").toString();
    }

}
