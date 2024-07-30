package com.greenmark.common.exception.datafeed;

import java.util.Date;

import com.greenmark.common.GmConstants;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PdbUpdateException</p>
 * <p>Description: </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class PdbUpdateException {
	public static final String CLASSNAME = "PdbUpdateException";

	/** Used by the database **/
	private long id; // leave as null for hibernate
	private int active = GmConstants.OBJECT_ACTIVE;
	/**************************/

	public Date exceptionTime;
	public int timeframe = -1;
	public long securityId = -1;

	private String description;

	public PdbUpdateException() {
	}

	public int getActive() {
		return active;
	}

	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}

	public long getSecurityId() {
		return securityId;
	}

	public int getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(int timeframe) {
		this.timeframe = timeframe;
	}

	public void setSecurityId(long securityId) {
		this.securityId = securityId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "active [" + active + "] ";
		return stb;
	}

	public Date getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

}
