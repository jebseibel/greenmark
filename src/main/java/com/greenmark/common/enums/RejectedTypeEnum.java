package com.greenmark.common.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: RejectedTypeEnum</p>
 * <p>Description: This enum is used to define the different reasons we reject a security.
 *  </p>
 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
**/

public enum RejectedTypeEnum {
	// @formatter:off
	PERMANENT("Bad Data", 1), 
	AGING("Aging", 2), 
	DISMISSABLE("Dismissable", 3),
	REJECTED_BY_USER("Rejected by User", 4);
	// @formatter:on

	private final String name;
	private final int id;

	RejectedTypeEnum(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getIdString() {
		return ((Integer) id).toString();
	}

	public static String[] getAllNames() {
		List<String> nameList = new ArrayList<>();
		nameList.add(PERMANENT.getName());
		nameList.add(AGING.getName());
		nameList.add(DISMISSABLE.getName());
		nameList.add(REJECTED_BY_USER.getName());

		String[] returnVal = new String[nameList.size()];
		return nameList.toArray(returnVal);
	}

	public static Optional<RejectedTypeEnum> getByName(String value) {
		return Arrays.stream(RejectedTypeEnum.values()).filter(accStatus -> accStatus.getName().equals(value)).findFirst();
	}

	public static Optional<RejectedTypeEnum> getById(int value) {
		return Arrays.stream(RejectedTypeEnum.values()).filter(accStatus -> accStatus.getId() == value).findFirst();
	}
}
