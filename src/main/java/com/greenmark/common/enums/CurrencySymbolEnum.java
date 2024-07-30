package com.greenmark.common.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: MomentumTypeEnum</p>
 * <p>Description: This enum is used to define the different currencies we use to purchase securities.
 *  </p>
 * @author  Monte Seibel
 * @version 8
 * @formatter:on
**/

public enum CurrencySymbolEnum {
	// @formatter:off
	USD("USD", 1), 
	USDT("USDT", 2), 
	USDC("USDC", 3);
	// @formatter:on

	private final String name;
	private final int id;

	CurrencySymbolEnum(String name, int id) {
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
		nameList.add(USD.getName());
		nameList.add(USDT.getName());
		nameList.add(USDC.getName());

		String[] returnVal = new String[nameList.size()];
		return nameList.toArray(returnVal);
	}

	public static Optional<CurrencySymbolEnum> getByName(String value) {
		return Arrays.stream(CurrencySymbolEnum.values()).filter(accStatus -> accStatus.getName().equals(value)).findFirst();
	}

	public static Optional<CurrencySymbolEnum> getById(int value) {
		return Arrays.stream(CurrencySymbolEnum.values()).filter(accStatus -> accStatus.getId() == value).findFirst();
	}
}
