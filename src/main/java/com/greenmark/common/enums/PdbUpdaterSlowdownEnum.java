package com.greenmark.common.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: PdbUpdaterSlowdownEnum</p>
 * <p>Description: This enum is used to define how slow we want the PdbUpdater to run.</p>

 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
 **/

public enum PdbUpdaterSlowdownEnum {
    // @formatter:off
	NO_SLOWDOWN(1, "NO_SLOWDOWN", 0), 
	FAST(2, "FAST", 1), 
	MEDIUM(3, "MEDIUM", 2), 
	SLOW(4, "SLOW", 3);
	// @formatter:on

    private final Integer id;
    private final String name;
    private final Integer slowdownSeconds;

    PdbUpdaterSlowdownEnum(Integer id, String name, Integer slowdownSeconds) {
        this.id = id;
        this.name = name;
        this.slowdownSeconds = slowdownSeconds;
    }

    public static Optional<PdbUpdaterSlowdownEnum> getByName(String value) {
        return Arrays.stream(PdbUpdaterSlowdownEnum.values()).filter(accStatus -> accStatus.getName().equals(value)).findFirst();
    }

    public static Optional<PdbUpdaterSlowdownEnum> getById(Integer value) {
        return Arrays.stream(PdbUpdaterSlowdownEnum.values()).filter(accStatus -> accStatus.getId() == value).findFirst();
    }

    public Integer getSlowdownSeconds() {
        return slowdownSeconds;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
