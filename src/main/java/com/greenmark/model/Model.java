package com.greenmark.model;

import com.greenmark.common.core.Labels;
import com.greenmark.utils.UTXmlUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class Model {
    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "Model";

    //These are the types
    //Base are ones that can be initialized
    //Instance are changed Base instances
    public static final int MODEL_TYPE_TEMPLATE = 1;
    public static final int MODEL_TYPE_INSTANCE = 2;


    /**
     * Used by the database
     **/
    private long id;            //leave as null for hibernate
    private int active = Labels.OBJECT_ACTIVE;
    /**************************/

    private String name;
    private String description = "";
    private Date modifiedDatetime;
    private String xml;
    private int type;

    public Model() {

    }

    public Model(String xmldata) {
        try {
            //ACCOUNT_ID
            this.id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");

            //SCENARIO_ID
            this.active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");

            //NAME
            this.name = UTXmlUtils.getXmlData(xmldata, "NAME");

            //DESCRIPTION
            this.description = UTXmlUtils.getXmlData(xmldata, "DESCRIPTION");

            //XML
            this.xml = xmldata;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
        }

    }

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

    public boolean isModelATemplate() {
        return type == MODEL_TYPE_TEMPLATE;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeString() {
        if (type == MODEL_TYPE_TEMPLATE)
            return "Template";
        else if (type == MODEL_TYPE_INSTANCE)
            return "Instance";
        else
            return "Unknown";
    }

}
