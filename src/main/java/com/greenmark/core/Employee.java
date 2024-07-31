package com.greenmark.core;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Seibel Consulting</p>
 *
 * @author not attributable
 * @version 1.0
 */

public class Employee {
    private static final String CLASSNAME = "Employee";

    private long id;
    private String username = null;
    private String password = null;
    private String firstName = null;
    private String lastName = null;
    private String emailAddress = null;
    private java.util.Date inactiveDate = null;
    private int active = 0;

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String dbSummary() {
        StringBuffer stb = new StringBuffer();
        stb.append(" > > " + CLASSNAME + " :: ");
        stb.append("id [" + id + "] ");
        stb.append("username [" + username + "] ");

        stb.append("password [" + password + "] ");
        stb.append("firstName [" + firstName + "] ");
        stb.append("lastName [" + lastName + "] ");
        stb.append("emailAddress [" + emailAddress + "] ");
        if (inactiveDate != null)
            stb.append("inactiveDate [" + inactiveDate + "] ");
        stb.append("active [" + active + "] ");
        return stb.toString();
    }

    public String toString() {
        return dbSummary();
    }

    public String getClassname() {
        return CLASSNAME;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public java.util.Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(java.util.Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

}
