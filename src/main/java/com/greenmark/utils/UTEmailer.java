package com.greenmark.utils;

import java.io.IOException;

import com.greenmark.common.exception.EmailException;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTEmailer</p>
 * <p>Description: This class is used by any application to format email messages.</p>
 *
 * @author Monte Seibel
 * @version 7.5
 * @formatter:on
 */

public class UTEmailer {
	public static final String CLASSNAME = "UTEmailer";

	public static String getHtmlForEmail(String inHTML_HeaderFile, String reportBody) throws EmailException, IOException {
		String methodname = "getHtmlForEmail";
		String returnVal = "";

		String basedir = UTPropertyManager.getProperty("base_directory");

		// Get the Information in the HTML file into a String object.
		String completeFilename = basedir + "/runtime/report_templates/nightly_service/" + inHTML_HeaderFile; // Create actual pathname to the file

		StringBuffer htmlContents = UTFileHandler.loadTextFile(completeFilename);
		returnVal = htmlContents.toString();
		returnVal += reportBody;

		return returnVal;
	}
}
