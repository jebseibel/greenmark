package com.greenmark.utils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTForward</p>
 * <p>Description: This deprecated class was used by a much older version of the web application.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

@Deprecated
public class UTForward {
    public static final String CLASSNAME = "UTForward";
    private String trace = "";

    public UTForward() {
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String traceParam) {
        this.trace = traceParam;
    }
}
