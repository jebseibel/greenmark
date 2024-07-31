package com.greenmark.utils;

import com.greenmark.common.GmConstants;

import java.time.LocalDateTime;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTNumdate</p>
 * <p>Description: A numdate is an integer representation for a date (epoch days).  We store numdates in the prices DB for quicker access times.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class UTNumdate {
    public static final String CLASSNAME = "UTNumdate";

    public static final int getTodaysNumdate() {
        return fromLDT(LocalDateTime.now());
    }

    // --------------------------- FROM METHODS -------------------------------
    public static final int fromLDT(LocalDateTime thisdate) {
        if (thisdate == null)
            return 0;

        int thisDayEpochI = (int) (UTDatetime.toEpoch(thisdate) / GmConstants.SECONDS_IN_DAY);
        return thisDayEpochI;
    }

    public static final int fromDate(java.util.Date thisdate) {
        if (thisdate == null)
            return 0;

        return fromLDT(UTDatetime.fromDate(thisdate));
    }

    public static final int fromString(String thisdate) {
        if (thisdate == null)
            return 0;

        return fromLDT(UTDatetime.fromLDTString(thisdate));
    }

    public static final int fromUTCalendarTime(UTCalendarTime thisdate) {
        if (thisdate == null)
            return 0;

        int numdays = fromDate(thisdate.getJavaDate());
        return numdays;
    }

}
