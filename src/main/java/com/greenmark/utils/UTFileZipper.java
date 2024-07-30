package com.greenmark.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTFileZipper</p>
 * <p>Description: This class is currently not used but remains for potential usage.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class UTFileZipper {
	public static final String CLASSNAME = "UTFileZipper";

	/**
	 * This writes out a file with absolute filepaths
	 *
	 * @param outputfile
	 * @param sourcedirs
	 */
	public static void zipDir(String outputfile, Vector sourcedirs) {
		UTFileZipper.zipDir(outputfile, sourcedirs, "");
	}

	/**
	 * This writes out a file with relative filepaths.
	 *
	 * @param outputfile
	 * @param sourcedirs
	 * @param ignorebase
	 */
	public static void zipDir(String outputfile, Vector sourcedirs, String ignorebase) {
		ZipOutputStream zipFile = null;
		try {
			// Create a Zip file stream.
			zipFile = new ZipOutputStream(new FileOutputStream(outputfile));

			// loop the directories
			for (Enumeration e = sourcedirs.elements(); e.hasMoreElements();) {
				String sourcedir = (String) e.nextElement();
				File dirfile = new File(sourcedir);

				File[] list = dirfile.listFiles();
				for (int i = 0; i < list.length; i++) {
					// get the next file
					File file = list[i];
					String label = file.getCanonicalPath();

					// if there is an ignorebase string, use it
					if (ignorebase.length() != 0) {
						label = label.substring(ignorebase.length());
					}

					// only add if it is a file
					if (!file.isDirectory()) {
						// read the file content into a byte array
						FileInputStream fis = new FileInputStream(file);
						long size = file.length();
						byte[] data = new byte[(int) size];
						int length = fis.read(data);
						fis.close();
						System.out.println("label [" + label + "]  size [" + data.length + "]");

						// create the zip entry
						ZipEntry entry = new ZipEntry(label);
						entry.setMethod(ZipEntry.DEFLATED);

						// add entry to the zip file
						zipFile.putNextEntry(entry);
						zipFile.write(data, 0, (int) size);
						zipFile.closeEntry();
					}
				}

				// Clean up the current file.
				zipFile.flush();
				zipFile.closeEntry();
			}

		} catch (Exception ex) {
			System.out.println("Caught exception in CLASS: " + CLASSNAME + ", message: [" + ex.getMessage() + "]");
		}
		// close the stream
		finally {
			try {
				if (zipFile != null) {
					zipFile.close();
				}
			} catch (Exception ex) {
				System.out.println("Caught exception in CLASS: " + CLASSNAME + ", message: [" + ex.getMessage() + "]");
			}
		}
	}

}