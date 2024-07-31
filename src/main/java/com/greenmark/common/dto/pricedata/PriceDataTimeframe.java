package com.greenmark.common.dto.pricedata;

import com.greenmark.common.GmConstants;
import com.greenmark.utils.UTTimeframe;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: PriceDataTimeframe</p>
 * <p>Description: A DTO for the Prices Database 'timeframe' table.  But this can also be used elsewhere as a timeframe object.</p>

 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
 **/

public class PriceDataTimeframe {
    public static final String CLASSNAME = "PriceDataTimeframe";

    private long id;
    private long updateJobId;

    private int timeframeEnumId; // From GMConstants: // TIMEFRAME ENUM VALUES
    private String description;

    private int active = GmConstants.OBJECT_ACTIVE;

    public PriceDataTimeframe() {
    }

    public PriceDataTimeframe(long updateJobId, int timeframeEnumId) {
        this.updateJobId = updateJobId;
        this.timeframeEnumId = timeframeEnumId;
        this.description = UTTimeframe.getDisplayStringForGmanTimeframe(timeframeEnumId);
    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUpdateJobId() {
        return updateJobId;
    }

    public void setUpdateJobId(long updateJobId) {
        this.updateJobId = updateJobId;
    }

    public int getTimeframeEnumId() {
        return timeframeEnumId;
    }

    public void setTimeframeEnumId(int timeframeEnumId) {
        this.timeframeEnumId = timeframeEnumId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

}
