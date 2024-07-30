package com.greenmark.common.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: BrokerTypeEnum</p>
 * <p>Description: This enum is used to define the different Brokers we use, either live or simulator.</p>

 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
**/

public enum BrokerTypeEnum {
	// @formatter:off
	TRAPPING_SIMULATOR("Trapping Simulator", 1), 
	KRAKEN_SIMULATOR("Kraken Simulator", 2), 
	KRAKEN_REALTIME("Kraken LIVE", 3);
	// @formatter:on

	private final String name;
	private final int id;

	BrokerTypeEnum(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static String[] getAllNames() {
		List<String> nameList = new ArrayList<>();
		nameList.add(TRAPPING_SIMULATOR.getName());
		nameList.add(KRAKEN_SIMULATOR.getName());
		nameList.add(KRAKEN_REALTIME.getName());

		String[] returnVal = new String[nameList.size()];
		return nameList.toArray(returnVal);
	}

	public static Optional<BrokerTypeEnum> getByName(String value) {
		return Arrays.stream(BrokerTypeEnum.values()).filter(accStatus -> accStatus.getName().equals(value)).findFirst();
	}

	public static Optional<BrokerTypeEnum> getById(int value) {
		return Arrays.stream(BrokerTypeEnum.values()).filter(accStatus -> accStatus.getId() == value).findFirst();
	}

}
