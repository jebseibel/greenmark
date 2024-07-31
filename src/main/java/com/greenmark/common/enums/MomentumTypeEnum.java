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
 * <p>Description: This enum is used to define the different momentum Technical Indicators we use:
 *    %K: Fast stochastic
 *    %D: Slow stochastic
 *    RSI: RSI Classic
 *  </p>
 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
 **/

public enum MomentumTypeEnum {
    // @formatter:off
	FAST_STOCHASTIC("%K", 1), 
	SLOW_STOCHASTIC("%D", 2), 
	RSI_CLASSIC("RSI", 3);
	// @formatter:on

    private final String name;
    private final int id;

    MomentumTypeEnum(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static String[] getAllNames() {
        List<String> nameList = new ArrayList<>();
        nameList.add(FAST_STOCHASTIC.getName());
        nameList.add(SLOW_STOCHASTIC.getName());
        nameList.add(RSI_CLASSIC.getName());

        String[] returnVal = new String[nameList.size()];
        return nameList.toArray(returnVal);
    }

    public static Optional<MomentumTypeEnum> getByName(String value) {
        return Arrays.stream(MomentumTypeEnum.values()).filter(accStatus -> accStatus.getName().equals(value)).findFirst();
    }

    public static Optional<MomentumTypeEnum> getById(int value) {
        return Arrays.stream(MomentumTypeEnum.values()).filter(accStatus -> accStatus.getId() == value).findFirst();
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
}
