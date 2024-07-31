package com.greenmark.common.core;

import java.util.Hashtable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: Timeslice</p>
 * <p>Description: This class is responsible for processing all timeslice data for a security.
 * Its main goal is to act as the center point for retrieving and processing
 * data.<p>
 *
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class Timeslice {
    public static final String CLASSNAME = "Timeslice";

    /**
     * The label constant for a stock timeslice's "date"
     */
    public static final String DATE = "date";

    /**
     * The label constant for a stock timeslice's "time"
     */
    public static final String TIME = "time";

    /**
     * The label constant for a stock timeslice's open executedPrice
     */
    public static final String OPEN = "open";

    /**
     * The label constant for a stock timeslice's high executedPrice
     */
    public static final String HIGH = "high";

    /**
     * The label constant for a stock timeslice's low executedPrice
     */
    public static final String LOW = "low";

    /**
     * The label constant for a stock timeslice's close executedPrice
     */
    public static final String CLOSE = "close";

    /**
     * The label constant for a stock timeslice's volume
     */
    public static final String VOLUME = "volume";

    // class properties
    Hashtable data;

    ////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////
    public Timeslice() {
        this.data = new Hashtable();
    }

    /**
     * Creates a new instance of a timeslice. This constructor is as simple as it gets. There is no pre-processing of any values.
     *
     * @param data
     */
    public Timeslice(Hashtable data) {
        this.data = data;
    }

    ////////////////////////////////////////////////////////////////////////////////
    // Public Methods
    ////////////////////////////////////////////////////////////////////////////////

    /**
     * @param label
     * @return
     */
    public final String getData(String label) {
        if (this.data.containsKey(label)) {
            return ((String) this.data.get(label));
        }
        return ("");
    }

    public final boolean isValid60MinSummary() {
        return (getHigh() != 0.0) && (getLow() != 10000.0) && (getOpen() != 0.0);
    }

    public final float getHigh() {
        if (this.data.containsKey(Timeslice.HIGH)) {
            String strHigh = (String) this.data.get(Timeslice.HIGH);
            return Float.parseFloat(strHigh);
        }
        return (0);
    }

    public final float getLow() {
        if (this.data.containsKey(Timeslice.LOW)) {
            String strLow = (String) this.data.get(Timeslice.LOW);
            return Float.parseFloat(strLow);
        }
        return (0);
    }

    public final float getOpen() {
        if (this.data.containsKey(Timeslice.OPEN)) {
            String strOpen = (String) this.data.get(Timeslice.OPEN);
            return Float.parseFloat(strOpen);
        }
        return (0);
    }

    public final float getClose() {
        if (this.data.containsKey(Timeslice.CLOSE)) {
            String strClose = (String) this.data.get(Timeslice.CLOSE);
            return Float.parseFloat(strClose);
        }
        return (0);
    }

    public final float getVolume() {
        if (this.data.containsKey(Timeslice.VOLUME)) {
            String strVolume = (String) this.data.get(Timeslice.VOLUME);
            return Float.parseFloat(strVolume);
        }
        return (0);
    }

    /**
     * @param label
     * @param value
     */
    public final void setData(String label, String value) {
        this.data.put(label, value);
    }

    /**
     * Appends and item of data to the stock's timeslice.
     *
     * @param label - the label for the data.
     * @param value = the value of the data.
     * @return
     */
    public final boolean appendData(String label, String value) {
        try {
            this.data.put(label, value);
            return (true);
        } catch (Throwable t) {
            return (false);
        }
    }

    /**
     * Retrieve the time for this timeslice.
     *
     * @return the time.
     */
    public final String getTime() {
        // We actually parse the Time off the DATE key. Which we set on create with the datetime.
        if (this.data.containsKey(DATE)) {
            String datetime = (String) this.data.get(DATE);
            // int spacePosition = datetime.lastIndexOf(" ", 12);
            // String date = datetime.substring(datetime.lastIndexOf(" ", 12) + 1, datetime.length() );
            return (datetime.substring(datetime.lastIndexOf(" ", 12) + 1));
        }

        return ("");
    }

    // This method returns the Hour out of this Timeslice's datetime
    public final String getTimeHour() {
        // We actually parse the Time off the DATE key. Which we set on create with the datetime.
        if (this.data.containsKey(DATE)) {
            String datetime = (String) this.data.get(DATE);
            // int spacePosition = datetime.lastIndexOf(" ", 12);
            String time = datetime.substring(datetime.lastIndexOf(" ", 12) + 1);
            // int colonPosition = time.indexOf(':');
            // String hour = time.substring ( 0, time.indexOf(':') );

            return (time.substring(0, time.indexOf(':')));
        }

        return ("");
    }

    // This method returns the Minute out of this Timeslice's datetime
    public final String getTimeMinute() {
        // We actually parse the Time off the DATE key. Which we set on create with the datetime.
        if (this.data.containsKey(DATE)) {
            String datetime = (String) this.data.get(DATE);
            // int spacePosition = datetime.lastIndexOf(" ", 12);
            String time = datetime.substring(datetime.lastIndexOf(" ", 12) + 1);
            int firstColonPos = time.indexOf(':');
            // int secondColonPos = time.indexOf(':', firstColonPos + 1);
            // String minute = time.substring (firstColonPos + 1, time.indexOf(':', firstColonPos + 1) );

            return (time.substring(firstColonPos + 1, time.indexOf(':', firstColonPos + 1)));
        }

        return ("");
    }

    // This method returns the Hour and Minute out of this Timeslice's datetime: HH:MM
    public final String getTimeHourMinute() {
        // We actually parse the Time off the DATE key. Which we set on create with the datetime.
        if (this.data.containsKey(DATE)) {
            String datetime = (String) this.data.get(DATE);
            // int spacePosition = datetime.lastIndexOf(" ", 12);
            String time = datetime.substring(datetime.lastIndexOf(" ", 12) + 1);
            int firstColonPos = time.indexOf(':');
            // int secondColonPos = time.indexOf(':', firstColonPos + 1);
            // String minute = time.substring (firstColonPos + 1, time.indexOf(':', firstColonPos + 1) );

            return (time.substring(0, time.indexOf(':', firstColonPos + 1)));
        }

        return ("");
    }

    /**
     * Retrieve the date for this timeslice.
     *
     * @return the date.
     */
    public final String getDate() {
        if (this.data.containsKey(DATE)) {
            String datetime = (String) this.data.get(DATE);
            // int spacePosition = datetime.lastIndexOf(" ", 12);
            // String date = datetime.substring(0,datetime.lastIndexOf(" ", 12));
            return (datetime.substring(0, datetime.lastIndexOf(" ", 12)));
        }
        return ("");
    }

    public final String getDatetime() {
        if (this.data.containsKey(DATE)) {
            return ((String) this.data.get(DATE));
        }
        return ("");
    }

    /**
     * Returns a string representation of this object.
     *
     * @return String
     */
    public String toString() {
        String date = (String) this.data.get(Timeslice.DATE);
        String high = (String) this.data.get(Timeslice.HIGH);
        String low = (String) this.data.get(Timeslice.LOW);
        String open = (String) this.data.get(Timeslice.OPEN);
        String close = (String) this.data.get(Timeslice.CLOSE);

        return ("Stock Timeslice: " + " datetime: " + date + ", high: " + high + ", low: " + low + ", open: " + open + ", close: " + close + ";");
    }
}
