package com.greenmark.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTDirectoryHandler</p>
 * <p>Description: This class is used by the trading client's XML save/restore to files feature.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class UTDirectoryHandler {
    public static final String CLASSNAME = "UTDirectoryHandler";
    public static long millsInDay = 86400000;

    ////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////

    public static String getXmlFileByDatetime(String directoryName, UTCalendarTime inDatetime, boolean twoUnderscoresInFilename) throws NumberFormatException {
        boolean codeDebug = false;

        SortedMap sm = getSortedDirectoryList(directoryName, twoUnderscoresInFilename);
        String returnFilename = "";

        String formattedInDatetime = inDatetime.formatFileDateTimeHelper(true);
        long formattedInDatetime_l = Long.parseLong(formattedInDatetime);

        if (codeDebug)
            System.out.println(
                    "Entering getXmlFileByDatetime,  directory: [" + directoryName + "]  inDatetime: [" + inDatetime.formatDateParam() + "]   formatted Datetime: [" + formattedInDatetime + "]");

        // get the last (most recent) key. Use that to get the latest filename.
        if (!sm.isEmpty()) {
            String lastFilename = "";
            Iterator fileListIter = sm.keySet().iterator();
            while (fileListIter.hasNext()) {
                String xmlFilename = (String) fileListIter.next();
                long xmlFilename_l = Long.parseLong(xmlFilename);

                if (codeDebug)
                    System.out.println("---------- Comparing xmlDatetime: [" + xmlFilename_l + "] to inDatetime: [" + formattedInDatetime_l + "]");

                if (xmlFilename_l == formattedInDatetime_l) // They are equal, set lastFilename to xmlFilename, and exit
                {
                    lastFilename = xmlFilename;

                    if (codeDebug)
                        System.out.println("---- EQUAL   - setting lastFilename: [" + lastFilename + "]");
                    break;
                }

                if (xmlFilename_l > formattedInDatetime_l) // xmlFilename is AFTER formattedInDatetime, just exit
                {
                    if (codeDebug)
                        System.out.println("---- XML After In   -  lastFilename: [" + lastFilename + "]");

                    break;
                }
                lastFilename = xmlFilename;
            }

            // Now that you found the correct key, get the filename value
            if (!lastFilename.equals("")) // we actually found one
                returnFilename = (String) sm.get(lastFilename);
        }

        if (codeDebug)
            System.out.println("Leaving getXmlFileByDatetime,  directory: [" + directoryName + "]  inDatetime: [" + inDatetime.formatDateParam() + "]   -  returnFilename: [" + returnFilename + "]");

        return returnFilename;
    }

    // This method only used by DialogLoadPreviousXml to get the XML datetime ranges.
    public static String getEarliestFile(String directoryName) {
        SortedMap sm = getSortedDirectoryList(directoryName, false); // Send false, we're using the stocks directory.

        // get the last (most recent) key. Use that to get the latest filename.
        if (!sm.isEmpty()) {
            String firstKey = (String) sm.firstKey();
            String firstValue = (String) sm.get(firstKey);
            return firstValue;
        }
        // if nothing there, return an empty string
        else {
            return "";
        }
    }

    public static String getLatestFile(String directoryName, boolean twoUnderscoresInFilename) {
        SortedMap sm = getSortedDirectoryList(directoryName, twoUnderscoresInFilename);

        // get the last (most recent) key. Use that to get the latest filename.
        if (!sm.isEmpty()) {
            String lastKey = (String) sm.lastKey();
            String lastValue = (String) sm.get(lastKey);
            return lastValue;
        }
        // if nothing there, return an empty string
        else {
            return "";
        }
    }

    public static SortedMap getSortedDirectoryList(String directoryName, boolean twoUnderscoresInFilename) {
        // read the directory contents into an array
        File directory = new File(directoryName);
        String[] arrayFile = directory.list();

        // create the sorting object. We are going to strip all
        // of the words and characters out of the filename and will be
        // left with just a number. That becomes the key
        SortedMap sm = new TreeMap();

        // loop the filenames, creating the key
        for (int i = 0; i < arrayFile.length; i++) {
            String longFilename = arrayFile[i];

            int p0 = longFilename.indexOf("_");
            if (twoUnderscoresInFilename) {
                int px = longFilename.indexOf("_", p0 + 1);
                p0 = px;
            }
            int p1 = longFilename.indexOf("-");
            int p2 = longFilename.lastIndexOf("-");
            int p3 = longFilename.lastIndexOf(".");

            // System.out.println( "size="+longFilename.length()+"; p0=" + p0 + "; p1=" + p1 + "; p2=" + p2 + "; p3=" + p3 + ";" );

            // ignore none formatted string
            if ((p0 != -1) && (p1 != -1) && (p2 != -1) && (p3 != -1) && (p1 != p2)) {
                String stb = longFilename.substring(p0 + 1, p1) +
                        longFilename.substring(p1 + 1, p2) +
                        longFilename.substring(p2 + 1, p3);

                // System.out.println( "stb :" + stb + ";" );
                sm.put(stb, longFilename);
            }
        }

        return sm;
    }

    /**
     * @param directoryName String
     * @param fileprefix    String
     * @return String
     */
    public static String getLatestFile(String directoryName, String fileprefix) {
        // read the directory contents into an array
        File directory = new File(directoryName);
        String[] arrayFile = directory.list();

        // create the sorting object. We are going to strip all
        // of the words and characters out of the filename and will be
        // left with just a number. That becomes the key
        SortedMap sm = new TreeMap();

        // loop the filenames, creating the key
        for (int i = 0; i < arrayFile.length; i++) {
            String longFilename = arrayFile[i];
            if (longFilename.startsWith(fileprefix)) {
                int p0 = longFilename.indexOf("_");
                int p1 = longFilename.indexOf("-");
                int p2 = longFilename.lastIndexOf("-");
                int p3 = longFilename.lastIndexOf(".");

                // System.out.println( "size="+longFilename.length()+"; p0=" + p0 + "; p1=" + p1 + "; p2=" + p2 + "; p3=" + p3 + ";" );

                // ignore none formatted string
                if ((p0 != -1) && (p1 != -1) && (p2 != -1) && (p3 != -1) && (p1 != p2)) {
                    String stb = longFilename.substring(p0 + 1, p1) +
                            longFilename.substring(p1 + 1, p2) +
                            longFilename.substring(p2 + 1, p3);

                    // System.out.println( "stb :" + stb + ";" );
                    sm.put(stb, longFilename);
                }
            }
        }

        // get the last (most recent) key. Use that to get the latest filename.
        if (!sm.isEmpty()) {
            String lastKey = (String) sm.lastKey();
            String lastValue = (String) sm.get(lastKey);
            return lastValue;
        }
        // if nothing there, return an empty string
        else {
            return "";
        }

    }

    /**
     * This method goes out to the specified directory and removes all but the number of files specified in the directory. The kept files are the latest ones (chronologically by name). That is, this
     * method purges old files. If the number of files in the directory is less than the number specified, than nothing is done.
     *
     * @param directoryName String The directory to work in
     * @param numToKeep     int Number of files to keep
     * @return String
     */
    public static int purgeFiles(String directoryName, int numToKeep) {
        log.info(" +++++++++ Entering UTDirectoryHandler.purgeFiles() +++++++++");

        // early exit for none
        if (numToKeep == -1)
            return 0;

        int numDeleted = 0;

        // read the directory contents into an array
        File directory = new File(directoryName);
        String[] arrayFile = directory.list();

        if (arrayFile.length <= numToKeep)
            return numDeleted;

        // create the sorting object. We are going to strip all
        // of the words and characters out of the filename and will be
        // left with just a number. That becomes the key
        SortedMap sm = new TreeMap();

        // loop the filenames, creating the key
        for (int i = 0; i < arrayFile.length; i++) {
            String longFilename = arrayFile[i];

            int p0 = longFilename.indexOf("_");
            int p1 = longFilename.indexOf("-");
            int p2 = longFilename.lastIndexOf("-");
            int p3 = longFilename.lastIndexOf(".");

            // ignore none formatted string
            if ((p0 != -1) && (p1 != -1) && (p2 != -1) && (p3 != -1) && (p1 != p2)) {
                String stb = longFilename.substring(p0 + 1, p1) +
                        longFilename.substring(p1 + 1, p2) +
                        longFilename.substring(p2 + 1, p3);

                // System.out.println( "stb :" + stb + ";" );
                sm.put(stb, longFilename);
            }
        }

        // get the last (most recent) key. Use that to get the latest filename.
        int cnt = 0;
        int stop = sm.size() - numToKeep;

        Iterator iter = sm.values().iterator();
        while (iter.hasNext()) {
            String filepathToDelete = directoryName + "/" + iter.next();
            // System.out.println(" . . . filepathToDelete ["+filepathToDelete+"]");
            try {
                File fileToDelete = new File(filepathToDelete);
                fileToDelete.delete();
            } catch (Exception ex) {
                log.error("Exception trying to delete the file [" + filepathToDelete + "] e [" + ex.getMessage() + "]");
            }
            cnt++;
            if (cnt == stop)
                break;
        }

        return cnt;
    }

    /**
     * This method goes out to the specified directory and removes all old files that are further back than the specified number of days. For example, if you pass in "30" for the numDaysToKeep it will
     * keep files 30 days back, but remove any files earlier than that.
     * <p>
     * The determination of the date is not taken from file, but from the filename.
     *
     * @param directoryName String The directory to work in
     * @param numDaysToKeep int Number of days back to keep files.
     * @return String
     */
    public static void purgeAppconfigFiles(String directoryName, int numDaysToKeep) {

        // early exit for none
        if (numDaysToKeep == -1)
            return;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        // read the directory contents into an array
        File directory = new File(directoryName);
        String[] arrayFile = directory.list();
        // System.out.println("arrayFile.length ["+arrayFile.length+"]");

        // compensate if they sent in the directory name with a trailing "/"
        if (!directoryName.endsWith("/")) {
            directoryName = directoryName + "/";
        }
        // System.out.println("directoryName ["+directoryName+"]");

        ///////////////////////////////////////////
        // create the Date object we will compare against
        ///////////////////////////////////////////

        // compute number of milliseconds back based on what they sent in
        long mills_back = numDaysToKeep * millsInDay;

        // get the current number of milliseconds for today
        java.util.Date today = new java.util.Date();
        String strToday = UTDatetime.getDateAsOneForFilename(today);
        Date today_date = formatter.parse(strToday, new ParsePosition(0));
        long mills_today = today_date.getTime();

        // subtract the millseconds back from today
        long mills_compare = mills_today - mills_back;
        // System.out.println(" . . . mills_back ["+mills_back+"]");
        // System.out.println(" . . . mills_today ["+mills_today+"]");
        // System.out.println(" . . . mills_compare ["+mills_compare+"]");

        // create a date from the mills back. This date now represents the
        // number of days back from today.
        Date compareDate = new Date(mills_compare);
        // System.out.println(" . . . compareDate ["+compareDate+"]");

        // loop the filenames, creating the key
        for (int i = 0; i < arrayFile.length; i++) {
            String longFilename = arrayFile[i];
            // System.out.println("\nlongFilename ["+longFilename+"]");
            int p0 = longFilename.lastIndexOf("_");
            int p1 = longFilename.indexOf("-");
            int p2 = longFilename.lastIndexOf("-");
            int p3 = longFilename.lastIndexOf(".");

            // System.out.println( " . . . size="+longFilename.length()+"; p0=" + p0 + "; p1=" + p1 + "; p2=" + p2 + "; p3=" + p3 + ";" );
            // ignore none formatted string
            if ((p0 != -1) && (p1 != -1) && (p2 != -1) && (p3 != -1) && (p1 != p2)) {
                // StringBuffer stb = new StringBuffer();
                String strDate = longFilename.substring(p0 + 1, p1);
                // System.out.println("strDate ["+strDate+"]");

                // create the date
                Date thisdate = formatter.parse(strDate, new ParsePosition(0));

                // compare this date, if before mark it for deletion
                if (compareDate.after(thisdate) || (numDaysToKeep == 0)) {
                    String filepathToDelete = directoryName + longFilename;
                    // System.out.println( " . . . deleting file [" + longFilename + "]" ) ;
                    try {
                        File fileToDelete = new File(filepathToDelete);
                        fileToDelete.delete();
                    } catch (Exception ex) {
                        log.error("Exception UTDirectoryHandler.purgeAppconfigFiles trying to delete the file [" + filepathToDelete + "] e [" + ex.getMessage() + "]");
                    }
                } else {
                    // System.out.println(" . . . keep ["+longFilename+"]");
                }
            }
        }
    }

    /**
     * This method goes out to the specified directory and removes all old files that are further back than the specified number of days. For example, if you pass in "30" for the numDaysToKeep it will
     * keep files 30 days back, but remove any files earlier than that.
     * <p>
     * The determination of the date is not taken from file, but from the filename.
     *
     * @param directoryName String The directory to work in
     * @param numDaysToKeep int Number of days back to keep files.
     * @return String
     */
    public static void purgeLogFiles(String directoryName, int numDaysToKeep) {
        log.info(" +++++++++ Entering UTDirectoryHandler.purgeLogFiles() +++++++++");

        // early exit for none
        if (numDaysToKeep == -1)
            return;

        int numDeleted = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatlog = new SimpleDateFormat("yyyy_MM_dd");

        // read the directory contents into an array
        File directory = new File(directoryName);
        String[] arrayFile = directory.list();
        // System.out.println("arrayFile.length ["+arrayFile.length+"]");

        if (arrayFile.length <= numDaysToKeep)
            return;

        // compensate if they sent in the directory name with a trailing "/"
        if (!directoryName.endsWith("/")) {
            directoryName = directoryName + "/";
        }
        // System.out.println("directoryName ["+directoryName+"]");

        ///////////////////////////////////////////
        // create the Date object we will compare against
        ///////////////////////////////////////////

        // compute number of milliseconds back based on what they sent in
        long mills_back = numDaysToKeep * millsInDay;

        // get the current number of milliseconds for today
        java.util.Date today = new java.util.Date();
        String strToday = UTDatetime.getDateAsOneForFilename(today);
        Date today_date = formatter.parse(strToday, new ParsePosition(0));
        long mills_today = today_date.getTime();

        // subtract the millseconds back from today
        long mills_compare = mills_today - mills_back;
        // System.out.println(" . . . mills_back ["+mills_back+"]");
        // System.out.println(" . . . mills_today ["+mills_today+"]");
        // System.out.println(" . . . mills_compare ["+mills_compare+"]");

        // create a date from the mills back. This date now represents the
        // number of days back from today.
        Date compareDate = new Date(mills_compare);
        // System.out.println(" . . . compareDate ["+compareDate+"]");

        // loop the filenames, creating the key
        for (int i = 0; i < arrayFile.length; i++) {
            String longFilename = arrayFile[i];
            // System.out.println("\nlongFilename ["+longFilename+"]");
            String matchname = "logfile_";
            int p1 = longFilename.indexOf(matchname);
            int p2 = longFilename.lastIndexOf(".");

            // System.out.println( " . . . size="+longFilename.length()+"; p1=" + p1 + "; p2=" + p2 + ";" );
            // ignore none formatted string
            if ((p1 != -1) && (p2 != -1)) {
                // StringBuffer stb = new StringBuffer();
                String strDate = longFilename.substring(p1 + matchname.length(), p2);
                // System.out.println("strDate ["+strDate+"]");

                // create the date
                Date thisdate = formatlog.parse(strDate, new ParsePosition(0));
                // System.out.println("thisdate ["+thisdate+"]");

                // compare this date, if before mark it for deletion
                if (compareDate.after(thisdate)) {
                    String filepathToDelete = directoryName + longFilename;
                    // System.out.println(" . . . filepathToDelete ["+filepathToDelete+"]");
                    try {
                        File fileToDelete = new File(filepathToDelete);
                        fileToDelete.delete();
                    } catch (Exception ex) {
                        log.error("Exception UTDirectoryHandler.purgeLogFiles trying to delete the file [" + filepathToDelete + "] e [" + ex.getMessage() + "]");
                    }
                } else {
                    // System.out.println(" . . . keep ["+longFilename+"]");
                }
            }
        }
    }

}
