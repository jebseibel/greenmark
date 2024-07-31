package com.greenmark.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTSmtpAuthenticator</p>
 * <p>Description: This class is used by the UTEmailer utility class when emails require smtp authentication.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTSmtpAuthenticator extends Authenticator {
    public static final String CLASSNAME = "UTSmtpAuthenticator";
    /**
     * The username and password authentication object that is needed for SMTP secure server authentication.
     **/
    private PasswordAuthentication userPass;

    public UTSmtpAuthenticator() {
    }

    /**
     * This constructor creates an UTSmtpAuthenticator object using the input username and password.
     *
     * @param user A username that has access privileges for the secure SMTP server.
     * @param pass The password for the user with access privileges for the secure SMTP server.
     * @return Constructor method returns this object.
     */
    public UTSmtpAuthenticator(String user, String pass) {
        userPass = new PasswordAuthentication(user, pass);
    }

    public static void main(String[] args) {
        UTSmtpAuthenticator UTSmtpAuthenticator1 = new UTSmtpAuthenticator();
    }

    /**
     * Public accessor to return the internal PasswordAuthentication object that is required for connection to the secure SMTP server.
     */
    public final PasswordAuthentication getPasswordAuthentication() {
        return userPass;
    }

}
