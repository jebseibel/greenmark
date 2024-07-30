package com.greenmark.common.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: CommissionFeeEnum</p>
 * <p>Description: This enum is used to define different Broker Commission Fee calculations.</p>

 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
**/

public enum CommissionFeeEnum {
	// @formatter:off
	FLAT_FEE("FLAT FEE", 1), 
	KUCOIN("KUCOIN", 2), 
	KUCOIN_KCS("KUCOIN-KCS", 3), 
	BINANCE_BUSD("BINANCE-BUSD", 4), 
	KRAKEN("KRAKEN", 5);
	// @formatter:on

	private final String name;
	private final int id;

	CommissionFeeEnum(String name, int id) {
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
		nameList.add(FLAT_FEE.getName());
		nameList.add(KUCOIN.getName());
		nameList.add(KUCOIN_KCS.getName());
		nameList.add(BINANCE_BUSD.getName());
		nameList.add(KRAKEN.getName());

		return nameList.toArray(new String[nameList.size()]);
	}

	public static Optional<CommissionFeeEnum> getByName(String value) {
		return Arrays.stream(CommissionFeeEnum.values()).filter(accStatus -> accStatus.getName().equals(value)).findFirst();
	}

	public static Optional<CommissionFeeEnum> getById(int value) {
		return Arrays.stream(CommissionFeeEnum.values()).filter(accStatus -> accStatus.getId() == value).findFirst();
	}
}
