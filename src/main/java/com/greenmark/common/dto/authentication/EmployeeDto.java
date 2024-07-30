package com.greenmark.common.dto.authentication;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: EmployeeDto</p>
 * <p>Description: This DTO is for the results database employees table.  It is used by the website's login feature.
 *    A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class EmployeeDto implements Serializable {
	private static final String CLASSNAME = "EmployeeDto";
	private static final long serialVersionUID = 1L;

	private long id;
	private int active = 0;

	private String username = null;
	private String password = null;
	private String firstName = null;
	private String lastName = null;
	private String emailAddress = null;
	private java.util.Date inactiveDate = null;

	public EmployeeDto() {
	}

	public long getId() {
		return id;
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

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
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

	public void setId(long id) {
		this.id = id;
	}

}
