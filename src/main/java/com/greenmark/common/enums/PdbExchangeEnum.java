package com.greenmark.common.enums;

import java.util.Arrays;
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

public enum PdbExchangeEnum {
	// @formatter:off
	REGRESSION("REGRESSION", 1),
	KRAKEN_LIVE("KRAKEN", 2),
	KRAKEN_RESEARCH("KRAKEN_RESEARCH", 3),
	COINBASE("COINBASE", 4),
	BINANCE("BINANCE", 5),
	KUCOIN("KUCOIN", 6),
	FOREX("FOREX", 7);
	// @formatter:on

	private final String symbol;
	private final int id;

	PdbExchangeEnum(String name, int id) {
		this.symbol = name;
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getId() {
		return id;
	}

	public static Optional<PdbExchangeEnum> getByName(String value) {
		return Arrays.stream(PdbExchangeEnum.values()).filter(accStatus -> accStatus.getSymbol().equals(value)).findFirst();
	}

	public static Optional<PdbExchangeEnum> getById(int value) {
		return Arrays.stream(PdbExchangeEnum.values()).filter(accStatus -> accStatus.getId() == value).findFirst();
	}
}
