package com.greenmark.utils;

import java.io.*;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTFileHandler</p>
 * <p>Description: This class contains convenience methods for accessing data in files and is used extensively by the trading client's XML save/restore capability.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class UTFileHandler {
    public static final String CLASSNAME = "UTFileHandler";

    ////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////

    /*
     * CLASS: UTFileHandler
     *
     * This class is set up to handle all 'file' related functions. Specifically: <ol> <li>Reading a file and returning it as a string <li>Reading a file and returning as a java.io.file object </ol>
     * Please add other functions as needed.
     *
     * Author: Jeb Seibel Version: 1 Date: 11/28/2000
     */
    public UTFileHandler() {
    }

    ////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////

    /**
     * Convenience method to read a text file and returns it as a StringBuffer.
     *
     * @param file The file to load.
     * @return StringBuffer
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static final StringBuffer loadTextFile(String filename) throws FileNotFoundException, IOException {
        return (loadTextFile(new File(filename)));
    }

    /**
     * Convenience method to read a text file and returns it as a StringBuffer.
     *
     * @param file The file to load.
     * @return StringBuffer
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static final StringBuffer loadTextFileDelimited(String file, String delim) throws FileNotFoundException, IOException {
        return (loadTextFileDelimited(new File(file), delim));
    }

    /**
     * Convenience method to read a text file and returns it as a StringBuffer.
     *
     * @param file The file to load.
     * @return StringBuffer
     * @throws IOException
     */
    public static final StringBuffer loadTextFile(File file) throws IOException {
        // setup
        StringBuffer stb = new StringBuffer(Integer.parseInt("" + file.length()) + 5120);

        // open file
        FileInputStream fs = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fs));
        String line = in.readLine();

        // loop to extract the entire file
        while (line != null) {
            stb.append(line);
            line = in.readLine();
        }
        in.close();
        fs.close();
        return (stb);
    }

    /**
     * Convenience method to read a text file and returns it as a StringBuffer.
     *
     * @param file The file to load.
     * @return StringBuffer
     * @throws IOException
     */
    public static final StringBuffer loadTextFileDelimited(File file, String delim) throws IOException {
        // setup
        StringBuffer stb = new StringBuffer(Integer.parseInt("" + file.length()) + 5120);

        // open file
        FileInputStream fs = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fs));
        String line = in.readLine();

        // loop to extract the entire file
        while (line != null) {
            stb.append(line + delim);
            line = in.readLine();
        }
        in.close();
        fs.close();
        return (stb);
    }

    /**
     * Convenience method to read a text file and returns it as a StringBuffer.
     *
     * @param file The file to load.
     * @return StringBuffer
     * @throws IOException
     */
    public static final String loadInputStream(FileInputStream fs, String delim) throws IOException {
        // setup
        StringBuffer stb = new StringBuffer(5120);

        // open file
        BufferedReader in = new BufferedReader(new InputStreamReader(fs));
        String line = in.readLine();

        // loop to extract the entire file
        while (line != null) {
            stb.append(line + delim);
            line = in.readLine();
        }
        in.close();
        fs.close();
        return (stb.toString());
    }

    /**
     * Convenience method to read a text file and returns it as a StringBuffer.
     *
     * @param file The file to load.
     * @return StringBuffer
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static final String readFileFromDisk(String file) throws FileNotFoundException, IOException {
        return (readFileFromDisk(new File(file)));
    }

    /**
     * Reads the file from disk into a stringbuffer variable.
     *
     * @return String
     * @params filename - the fully qualified filename and path.
     */
    public static String readFileFromDisk(File file) throws IOException {
        // setup
        int fd = 0;
        StringBuffer stb = new StringBuffer(Integer.parseInt("" + file.length()) + 5120);

        // read template file into class variable
        FileReader fis = new FileReader(file);
        fd = fis.read();
        while (fd != -1) {
            stb.append((char) fd);
            fd = fis.read();
        }
        fis.close();

        return (stb.toString());
    }

    /**
     * writes a string to a file.
     *
     * @param filename
     * @param data
     * @return boolean
     * @throws IOException if it cant write the file
     */
    public static void writeFileToDisk(String filename, String data) throws IOException {
        // first fix slashes to be the right way for the os
        File dirfile = new File(filename);
        String str_osdirfile = dirfile.toString();

        // create a new file with the correct slashes
        File osdirfile = new File(str_osdirfile);
        String str_parent = osdirfile.getParent();

        // create the parent directories
        File osdirectory = new File(str_parent);
        osdirectory.mkdirs();

        // now write the file
        FileWriter fileout = new FileWriter(osdirfile);
        fileout.write(data);
        fileout.close();
    }

    /**
     * Reads in the binary/byte file and returns the byte array.
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] readBinaryFileFromDisk(String filename) throws FileNotFoundException, IOException {
        File file = new File(filename);

        // read the file content into a byte array
        FileInputStream fis = new FileInputStream(file);
        long size = file.length();
        byte[] data = new byte[(int) size];
        int length = fis.read(data);
        fis.close();

        return data;
    }

    /**
     * Writes a byte array to a file
     *
     * @param filename
     * @param data
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] writeBinaryFileToDisk(String filename, byte[] data) throws FileNotFoundException, IOException {
        // read the file content into a byte array
        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(data);
        fos.close();

        return data;
    }

}
