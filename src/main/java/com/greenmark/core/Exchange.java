package com.greenmark.core;

import com.greenmark.common.core.Labels;

import java.util.Set;
//import com.greenman.database.hibernate.HiberObject;

public class Exchange {
    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "Exchange";
    /**************************/

    public static final int TYPE_NYSE = 1;
    /**
     * Used by the database
     **/
    private long id;   //leave as null for hibernate
    private int active = Labels.OBJECT_ACTIVE;
    private String symbol;
    private String name;

    private Set securities;
    private Long security_id;

    /**
     *
     */
    public Exchange() {
        // Hibernate needs an empty constructor
    }

    public static int getTYPE_NYSE() {
        return TYPE_NYSE;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "active [" + active + "] ";
        return stb;
    }

    public String getClassname() {
        return CLASSNAME;
    }

    public Set getSecurities() {
        return securities;
    }

    public void setSecurities(Set securities) {
        this.securities = securities;
    }

    public Long getSecurity_id() {
        return security_id;
    }

    public void setSecurity_id(Long security_id) {
        this.security_id = security_id;
    }


}
