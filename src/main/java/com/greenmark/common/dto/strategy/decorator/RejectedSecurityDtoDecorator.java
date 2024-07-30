package com.greenmark.common.dto.strategy.decorator;

import java.io.Serializable;

import com.greenmark.common.GmConstants;
import com.greenmark.common.dto.strategy.RejectedSecurityDto;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTLabels;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: RejectedSecurityDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its RejectedSecurityDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class RejectedSecurityDtoDecorator extends RejectedSecurityDto implements Serializable {
	public static final String CLASSNAME = "RejectedSecurityDtoDecorator";
	private static final long serialVersionUID = 1L;

	public RejectedSecurityDtoDecorator() {
		super();
	}

//	public RejectedSecurityDtoDecorator(String xmldata) {
//		super(xmldata, trace);
//	}

	public final String getLongOrShortString() {
		if (longOrShort == GmConstants.SHORT_SECURITY)
			return "Short";
		if (longOrShort == GmConstants.LONG_SECURITY)
			return "Long";
		return "None";
	}

	public String getRejectedFromTimeframeString() {
		return UTLabels.getGmanLabelForGmanTimeframe(rejectedFromTimeframe);
	}

	public String getRestoredToModelDatetimeString() {
		return UTDatetime.toString(restoredToModelDatetime);
	}

	public String getRejectedDatetimeString() {
		return UTDatetime.toString(rejectedDatetime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		RejectedSecurityDtoDecorator other = (RejectedSecurityDtoDecorator) obj;

		if (!rejectedReason.equals(other.rejectedReason)) {
			return false;
		}

		if (!description.equals(other.description)) {
			return false;
		}

		if (!rejectedDatetime.equals(other.rejectedDatetime)) {
			return false;
		}

        return rejectedFromTimeframe == other.rejectedFromTimeframe;
    }
}
