package com.greenmark.common.dto.strategy;

import java.io.Serializable;
import java.util.Date;

import com.greenmark.common.GmConstants;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: StrategyDto</p>
 * <p>Description: This DTO is for the results database strategies table.  
 *
 *      A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 *    DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class StrategyDto implements Serializable {
    public static final String CLASSNAME = "StrategyDto";
    // These are the types
    // Base are ones that can be initialized
    // Instance are changed Base instances
    public static final int MODEL_TYPE_TEMPLATE = 1;
    public static final int MODEL_TYPE_INSTANCE = 2;
    private static final long serialVersionUID = 1L;
    private long id;
    private int active = GmConstants.OBJECT_ACTIVE;

    private String name;
    private String description = "";
    private Date modifiedDatetime;
    private String xml;
    private int type;

    public StrategyDto() {
    }

    // NOTE: This class doesn't need to save/restore to XML for the TradingClient. This constructor is used when initializing the Config class.
    public StrategyDto(String xmldata) {
        try {
            this.id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
            this.active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");
            this.name = UTXmlUtils.getXmlData(xmldata, "NAME");
            this.description = UTXmlUtils.getXmlData(xmldata, "DESCRIPTION");

            this.xml = xmldata;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
        }
    }

    // ------------------------------------------------ CONVENIENCE METHODS ---------------------------------------------------
    public String dbSummary() {
        StringBuffer stb = new StringBuffer();
        stb.append(" > > " + CLASSNAME + " :: ");
        stb.append("id [" + id + "] ");

        if (xml.length() > 10) {
            stb.append("xml snippet [" + xml.substring(0, 10) + "] length=" + xml.length() + " ");
        } else {
            stb.append("xml snippet [" + xml + "] ");
        }
        stb.append("name [" + name + "] ");
        stb.append("datetime [" + modifiedDatetime + "] ");
        stb.append("active [" + active + "] ");
        return stb.toString();
    }

    public boolean isModelATemplate() {
        return type == MODEL_TYPE_TEMPLATE;
    }

    public String getTypeString() {
        if (type == MODEL_TYPE_TEMPLATE)
            return "Template";
        else if (type == MODEL_TYPE_INSTANCE)
            return "Instance";
        else
            return "Unknown";
    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int value) {
        this.active = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getModifiedDatetime() {
        return modifiedDatetime;
    }

    public void setModifiedDatetime(Date modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }

    public String getClassname() {
        return CLASSNAME;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
