package com.greenmark.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
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
}
