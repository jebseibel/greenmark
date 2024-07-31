package com.greenmark.utils.trace;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: TraceMessageListener</p>
 * <p>Description: </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public interface TraceMessageListener {
    void addMessage(String message);
    // public boolean traceReady();

    String getListenerName();
}
