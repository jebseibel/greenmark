package com.greenmark.utils;

import com.greenmark.common.GmConstants;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTDbUtils</p>
 * <p>Description: This class contains utility methods for database access.</p>
 *
 * @author Monte Seibel
 * v
 * @formatter:on
 */
@Slf4j
public class UTDbUtils {
    public static final String CLASSNAME = "UTDbUtils";

    public static int getBooleanInt(boolean input) {
        if (input)
            return 1;
        else
            return 0;
    }

    public static boolean getBooleanInt(int input) {
        return input == 1;
    }

    public static boolean isTrueOrFalseString(String inString) {
        if (GmConstants.TRUE.equals(inString))
            return true;
        return GmConstants.FALSE.equals(inString);
    }

    public static final java.sql.Date todaysSqlDate() {
        java.util.Date todaysDate = new java.util.Date();
        return new java.sql.Date(todaysDate.getTime());
    }

    public static final java.util.Date sqlDateToJavaDate(java.sql.Date inDate) {
        if (inDate == null)
            return null;

        return new java.util.Date(inDate.getTime());
    }

    public static final int getNextId(Connection conn, String seqname) throws Exception {
        String methodname = "getNextId";

        // create the needed sql objects
        Statement stmt = null;
        ResultSet rs = null;
        int result = -1;

        // early exit on error
        if (seqname.length() == 0 || seqname == null) {
            String msg = " . . . Null or empty sequence name sent in to " + CLASSNAME + ":" + methodname + "()";
            log.debug(msg);
            throw new Exception(msg);
        }

        try {
            // create a timestamp
            Timestamp ts = new Timestamp(System.currentTimeMillis());

            // use a statement
            // log.warn(" . . . get the prepared statement");
            stmt = conn.createStatement();

            // set up the sql
            String sql = "select nextval('" + seqname + "') as next_val";

            // execute the query
            rs = stmt.executeQuery(sql);

            // get the results
            rs.next();
            result = rs.getInt("next_val");

        } catch (Exception ex) {
            // log the exception and then rethrow it
            log.error("Caught General Exception " + CLASSNAME + "." + methodname + ":[" + ex.getMessage() + "]");
            throw new Exception(ex.getMessage());
        } finally {
            try {
                // close the statement
                if (rs != null)
                    rs.close();

                // close the statement
                if (stmt != null)
                    stmt.close();
            } catch (Exception ex) {
                log.error("Exception closing sql objects " + CLASSNAME + "." + methodname + ":[" + ex.getMessage() + "]");
                throw new Exception(ex.getMessage());
            }
        }

        return result;

    }

}
